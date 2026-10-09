package com.nclh.hooks;

import com.nclh.constants.GlobalConstants;
import com.nclh.dataProviders.ConfigFileReader;
import com.nclh.enums.ClientEnvironment;
import com.nclh.managers.AllDriverManager;
import com.nclh.managers.FileReaderManager;
import com.nclh.managers.TestRailManager;
import com.nclh.utilities.APIException;
import com.nclh.utilities.CommonFunctions;
import com.nclh.utilities.Context;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.openqa.selenium.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.nclh.retryFailedTests.RetryTracker.getCurrentRetryCountForScenario;
@Log4j2
public class WebHooks {

    AllDriverManager allDriverManager;
    WebDriver webDriver;
    List<String> screenshotPaths;
    Context context;
    ConfigFileReader configFileReader;
    boolean updateTestRail;

    private final Path downloadsPath = Path.of(System.getProperty("user.dir"), "downloads");
    private final File downloads = downloadsPath.toFile();

    private final Path generatedPDFPath = Path.of(System.getProperty("user.dir"), "target", "generatedPdfs");
    private final File generatedPDFs = generatedPDFPath.toFile();

    public WebHooks(Context context) {
        allDriverManager = new AllDriverManager();
        screenshotPaths = new ArrayList<>();
        this.context = context;
        this.configFileReader = new ConfigFileReader();
        this.updateTestRail = configFileReader.isUpdateTestRail();

    }

    @Before
    public void setUp() {
        log.info("========== Test Setup Started ==========");
        log.info("Checking downloads directory at: {}", downloads.getAbsolutePath());
        String serverName = configFileReader.getServerName();
        GlobalConstants.TEST_ENVIRONMENT = ClientEnvironment.fromServerName(serverName);
        System.out.println("Running tests on server: " + GlobalConstants.TEST_ENVIRONMENT);
        System.out.println("Ship type is : " + GlobalConstants.CLIENT_TYPE);

        if (!downloads.exists()) {
            log.warn("Downloads directory does not exist. Attempting to create...");
            boolean isCreated = downloads.mkdirs();

            if (isCreated) {
                log.info("Downloads directory created successfully.");
            } else {
                log.error("Failed to create downloads directory at: {}", downloads.getAbsolutePath());
                throw new RuntimeException("Failed to create downloads directory at: " + downloads.getAbsolutePath());
            }
        } else {
            log.info("Downloads directory already exists.");
        }

        log.info("Clearing downloads folder...");
        CommonFunctions.clearFolder(downloads);
        log.info("Downloads folder cleared.");

        log.info("Initializing WebDriver...");
        allDriverManager.createDriver();
        webDriver = allDriverManager.getDriver();
        log.info("WebDriver initialized successfully.");

        String url = FileReaderManager.getInstance().getConfigFileReader().getUrl();
        log.info("Navigating to URL: {}", url);
        webDriver.get(url);

        log.info("Refreshing the browser...");
        webDriver.navigate().refresh();

        log.info("========== Test Setup Completed ==========");
    }

    private String extractTestCaseIdFromTags(Scenario scenario) {
        log.info("Starting extraction of test case ID from scenario tags");

        String tagName = "";

        for (String tag : scenario.getSourceTagNames()) {
            log.debug("Processing tag: {}", tag);

            if (tag.matches("@C\\d+")) {
                tagName = tag.substring(2);  // Strip @C from the tag
                log.info("Matched test case ID tag: {} -> Extracted ID: {}", tag, tagName);
            }
        }

        if (tagName.isEmpty()) {
            log.warn("No test case ID found in scenario tags: {}", scenario.getSourceTagNames());
            // Uncomment below if you want to enforce failure
            // throw new IllegalArgumentException("Test case ID not found in scenario tags");
        } else {
            log.info("Final extracted test case ID: {}", tagName);
        }

        log.info("Completed extraction of test case ID");

        return tagName;
    }

    private List<String> extractTestCaseIds(Scenario scenario) {
        log.info("Starting extraction of test case IDs from scenario: {}", scenario.getName());

        List<String> tags = new ArrayList<>();

        for (String tag : scenario.getSourceTagNames()) {
            log.debug("Processing tag: {}", tag);

            if (tag.matches("@C\\d+")) {
                String testCaseId = tag.substring(2);
                log.info("Found test case ID: {}", testCaseId);
                tags.add(testCaseId);
            }
        }

        log.info("Completed extraction. Total test case IDs found: {}", tags.size());
        log.debug("Extracted test case IDs: {}", tags);

        return tags;
    }

    @After(order = 1)
    public void tearDown(Scenario scenario) throws APIException, IOException {
        log.info("===== Starting tearDown for scenario: {} =====", scenario.getName());

        String testCaseId = extractTestCaseIdFromTags(scenario);
        List<String> testCaseIds = extractTestCaseIds(scenario);
        List<Integer> testCaseIdsList = new ArrayList<>();

        log.debug("Extracted testCaseId: {}", testCaseId);
        log.debug("Extracted testCaseIds list: {}", testCaseIds);

        int caseId = 0;

        if (testCaseIds.size() == 1) {
            caseId = Integer.parseInt(testCaseId);
            log.info("Single TestCase detected. caseId: {}", caseId);

            captureScreenshot(allDriverManager.getDriver(), testCaseId);
            log.debug("Screenshot captured for testCaseId: {}", testCaseId);

        } else {
            log.info("Multiple TestCases detected: {}", testCaseIds.size());

            for (String id : testCaseIds) {
                int parsedId = Integer.parseInt(id);
                testCaseIdsList.add(parsedId);

                log.debug("Processing testCaseId: {}", parsedId);
                captureScreenshot(allDriverManager.getDriver(), id);
            }
        }

        int statusId = scenario.isFailed() ? 5 : 1;
        String comment = scenario.isFailed() ? scenario.getName() + " failed" : "Test passed";

        log.info("Scenario status: {}", scenario.isFailed() ? "FAILED" : "PASSED");
        log.debug("StatusId: {}, Comment: {}", statusId, comment);

        byte[] screenshot = new byte[0];

        if (scenario.isFailed()) {
            log.warn("Scenario failed. Capturing screenshot for failure.");

            try {
                screenshot = ((TakesScreenshot) allDriverManager.getDriver())
                        .getScreenshotAs(OutputType.BYTES);

                scenario.attach(screenshot, "image/png", "screenshot");
                log.debug("Failure screenshot attached to report.");

            } catch (WebDriverException e) {
                log.error("Screenshot capture not supported: {}", e.getMessage(), e);
            }

            if (testCaseIds.size() == 1) {
                log.info("Processing single failed test case: {}", testCaseId);

                if (screenshotPaths.size() > 0) {
                    log.debug("Creating PDF from screenshots for testCaseId: {}", testCaseId);
                    createPdfFromScreenshots(screenshotPaths, testCaseId + "_screenshot.pdf");
                }

                if (!updateTestRail) {
                    log.info("updateTestRail is set to false. Skipping TestRail update for testCaseId: {}", testCaseId);
                } else if (screenshotPaths.size() > 0) {
                    TestRailManager.addResultForTestCase(
                            Integer.parseInt(testCaseId), statusId, comment,
                            generatedPDFPath.resolve(testCaseId + "_screenshot.pdf").toString()
                    );
                } else {
                    log.debug("Uploading single screenshot for testCaseId: {}", testCaseId);

                    TestRailManager.addResultForTestCase(
                            Integer.parseInt(testCaseId), statusId, comment,
                            System.getProperty("user.dir") + "/testRailImages/" + testCaseId + ".png"
                    );
                }

            } else {
                log.info("Processing multiple failed test cases.");

                for (int i = 0; i < testCaseIdsList.size(); i++) {
                    try {
                        if (screenshotPaths.size() > 0) {
                            createPdfFromScreenshots(
                                    screenshotPaths,
                                    testCaseIdsList.get(0) + "_screenshot.pdf"
                            );
                        }

                        if (!updateTestRail) {
                            log.info("updateTestRail is set to false. Skipping TestRail update for testCaseId: {}", testCaseIdsList.get(i));
                        } else if (screenshotPaths.size() > 0) {
                            TestRailManager.addResultForTestCase(
                                    testCaseIdsList.get(i), statusId, comment,
                                    generatedPDFPath.resolve(testCaseIdsList.get(0) + "_screenshot.pdf").toString()
                            );
                        } else {
                            TestRailManager.addResultForTestCase(
                                    testCaseIdsList.get(i), statusId, comment,
                                    System.getProperty("user.dir") + "/testRailImages/" + testCaseIdsList.get(i) + ".png"
                            );
                        }

                        log.debug("Processed testCaseId: {}", testCaseIdsList.get(i));

                    } catch (APIException ae) {
                        log.error("TestCaseId {} not present in current run",
                                testCaseIdsList.get(i), ae);
                    }
                }
            }

        } else {
            if (testCaseIds.size() == 1) {
                log.info("Scenario passed. Processing single testCaseId: {}", caseId);

                if (screenshotPaths.size() > 0) {
                    createPdfFromScreenshots(screenshotPaths, testCaseId + "_screenshot.pdf");
                }

                if (!updateTestRail) {
                    log.info("updateTestRail is set to false. Skipping TestRail update for testCaseId: {}", caseId);
                } else if (screenshotPaths.size() > 0) {
                    TestRailManager.addResultForTestCase(
                            caseId, statusId, comment,
                            generatedPDFPath.resolve(testCaseId + "_screenshot.pdf").toString()
                    );
                } else {
                    TestRailManager.addResultForTestCase(
                            caseId, statusId, comment,
                            System.getProperty("user.dir") + "/testRailImages/" + testCaseId + ".png"
                    );
                }

                log.debug("Processed passed testCaseId: {}", caseId);

            } else {
                log.info("Scenario passed. Processing multiple testCaseIds.");

                for (int i = 0; i < testCaseIdsList.size(); i++) {
                    try {
                        if (screenshotPaths.size() > 0) {
                            createPdfFromScreenshots(
                                    screenshotPaths,
                                    testCaseIdsList.get(0) + "_screenshot.pdf"
                            );
                        }

                        if (!updateTestRail) {
                            log.info("updateTestRail is set to false. Skipping TestRail update for testCaseId: {}", testCaseIdsList.get(i));
                        } else if (screenshotPaths.size() > 0) {
                            TestRailManager.addResultForTestCase(
                                    testCaseIdsList.get(i), statusId, comment,
                                    generatedPDFPath.resolve(testCaseIdsList.get(0) + "_screenshot.pdf").toString()
                            );
                        } else {
                            TestRailManager.addResultForTestCase(
                                    testCaseIdsList.get(i), statusId, comment,
                                    System.getProperty("user.dir") + "/testRailImages/" + testCaseIdsList.get(0) + ".png"
                            );
                        }

                        log.debug("Processed testCaseId: {}", testCaseIdsList.get(i));

                    } catch (APIException ae) {
                        log.error("TestCaseId {} not present in current run",
                                testCaseIdsList.get(i), ae);
                    }
                }
            }
        }

        log.info("===== Completed tearDown for scenario: {} =====", scenario.getName());
    }

    public void captureScreenshot(WebDriver driver, String testCaseId) throws IOException  {
        log.info("Starting screenshot capture for Test Case ID: {}", testCaseId);

        try {
            String getCurrentDirectory = System.getProperty("user.dir");
            log.debug("Current working directory: {}", getCurrentDirectory);

            String screenshotPath = getCurrentDirectory + "/testRailImages/" + testCaseId + ".png";
            log.info("Screenshot will be saved at: {}", screenshotPath);

            takeFullPageScreenshot(driver, screenshotPath);

            log.info("Screenshot captured successfully for Test Case ID: {}", testCaseId);
        } catch (Exception e) {
            log.error("Unexpected error while capturing screenshot for Test Case ID: {}", testCaseId, e);
            throw new RuntimeException("Failed to capture screenshot", e);
        }
    }

    @After(order = 0)
    public void killSession() {
        log.info("Attempting to close the WebDriver session");
        try {
            allDriverManager.closeDriver();
            log.info("WebDriver session closed successfully");
        } catch (Exception e) {
            log.error("Error occurred while closing WebDriver session", e);
            throw e; // rethrowing to ensure failure is not swallowed
        }
    }

      @AfterStep
    public void captureScreensShotForEveryStep(Scenario scenario) throws IOException {
          log.info("Starting captureScreensShotForEveryStep for scenario: {}", scenario.getName());
          // CHECK SKIP FLAG

          String skipFlag = context.getContext("SKIP_SCREENSHOT");
          boolean skipScreenshot = skipFlag != null && Boolean.parseBoolean(skipFlag);

          if (skipScreenshot) {
              log.info("Skipping screenshot for this step as SKIP_SCREENSHOT = true");

              context.setContext("SKIP_SCREENSHOT", "false"); // reset
              return;
          }


          int statusId = scenario.isFailed() ? 5 : 1;  // 5=Failed, 1=Passed
          String comment = scenario.isFailed() ? scenario.getName() + " failed" : "Test passed";
          log.info("Scenario statusId: {}, comment: {}", statusId, comment);

          byte[] screenshot = new byte[0];

          String testCaseId = extractTestCaseIdFromTags(scenario);
          List<String> testCaseIds = extractTestCaseIds(scenario);
          List<Integer> testCaseIdsList = new ArrayList<>();

          CommonFunctions commonFunctions = new CommonFunctions();
          int caseId = 0;

          try {
              if (testCaseIds.size() == 1) {
                  caseId = Integer.parseInt(testCaseId);
                  log.info("Single testCaseId detected: {}", caseId);

                  String screenshotName = testCaseId + commonFunctions.getRandomNumber(1000, 100000);
                  log.debug("Capturing screenshot for PDF with name: {}", screenshotName);

                  screenshotPaths.add(captureScreenshotForPDF(screenshotName));
              } else {
                  log.info("Multiple testCaseIds detected, using first one");

                  testCaseIdsList.add(Integer.parseInt(testCaseIds.get(0)));

                  String screenshotName = testCaseIds.get(0) + commonFunctions.getRandomNumber(1000, 100000);
                  log.debug("Capturing screenshot for PDF with name: {}", screenshotName);

                  screenshotPaths.add(captureScreenshotForPDF(screenshotName));
              }

              log.info("Capturing screenshot for scenario attachment");

              byte[] screenshotPass = ((TakesScreenshot) allDriverManager.getDriver())
                      .getScreenshotAs(OutputType.BYTES);

              scenario.attach(screenshotPass, "image/png", "screenshot");

              log.info("Screenshot successfully attached to scenario: {}", scenario.getName());

          } catch (Exception e) {
              log.error("Error while capturing screenshot for scenario: {}", scenario.getName(), e);
              throw e;
          }
      }

    public String captureScreenshotForPDF(String stepName) throws IOException {
        log.info("Starting screenshot capture for step: {}", stepName);

        try {
            // Capture screenshot
            File srcFile = ((TakesScreenshot) allDriverManager.getDriver())
                    .getScreenshotAs(OutputType.FILE);
            log.debug("Screenshot captured in memory for step: {}", stepName);

            String filePath = "downloads/" + stepName + ".png";
            File destFile = new File(filePath);

            log.info("Saving screenshot to path: {}", destFile.getAbsolutePath());

            // Save the screenshot to the desired path
            FileUtils.copyFile(srcFile, destFile);

            log.info("Screenshot successfully saved for step: {}", stepName);

            return destFile.toString();

        } catch (IOException e) {
            log.error("Failed to capture/save screenshot for step: {}", stepName, e);
            throw e; // rethrowing to maintain existing behavior
        } catch (Exception e) {
            log.error("Unexpected error during screenshot capture for step: {}", stepName, e);
            throw new RuntimeException("Error while capturing screenshot for step: " + stepName, e);
        }
    }

    public void createPdfFromScreenshots(List<String> screenshotPaths, String outputPdf) throws IOException {
        log.info("Starting PDF creation. Output file: {}", outputPdf);

        PDDocument document = new PDDocument();

        try {
            if (screenshotPaths == null || screenshotPaths.isEmpty()) {
                log.warn("No screenshots provided. PDF will be empty.");
            }
            //  Create directory if not exists
            if (generatedPDFs != null && !generatedPDFs.exists()) {
                boolean created = generatedPDFs.mkdirs();
                if (created) {
                    log.info("Created directory for PDF: {}", generatedPDFs.getAbsolutePath());
                } else {
                    log.warn("Failed to create directory or already exists: {}", generatedPDFs.getAbsolutePath());
                }
            }

            File outputFile = new File(generatedPDFs, outputPdf);
            outputPdf = outputFile.getPath();

            int pageCount = 0;

            for (String path : screenshotPaths) {
                log.info("Processing screenshot: {}", path);

                try {
                    PDPage page = new PDPage();
                    document.addPage(page);

                    PDImageXObject pdImage = PDImageXObject.createFromFile(path, document);
                    PDPageContentStream contentStream = new PDPageContentStream(document, page);

                    contentStream.drawImage(pdImage, 20, 100, 500, 300);
                    contentStream.close();

                    pageCount++;
                    log.debug("Added image to PDF page successfully: {}", path);

                } catch (Exception e) {
                    log.error("Failed to process screenshot: {}", path, e);
                }
            }

            log.info("Total pages added to PDF: {}", pageCount);

            document.save(outputPdf);
            log.info("PDF saved successfully at: {}", outputPdf);

        } catch (Exception e) {
            log.error("Error occurred while creating PDF: {}", outputPdf, e);
            throw e;
        } finally {
            try {
                document.close();
                log.info("PDF document closed successfully.");
            } catch (IOException e) {
                log.error("Error while closing PDF document", e);
            }
        }
    }

    public static void takeFullPageScreenshot(WebDriver driver, String filePath) {
        log.info("Starting full page screenshot capture. Output path: {}", filePath);

        try {
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,0)", "");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.error("Interrupted while waiting before starting screenshot capture", e);
            Thread.currentThread().interrupt();
        }

        // Get total page height
        Long totalHeight = (Long) ((JavascriptExecutor) driver).executeScript(
                "return Math.max(document.body.scrollHeight, document.body.offsetHeight, " +
                        "document.documentElement.clientHeight, document.documentElement.scrollHeight, " +
                        "document.documentElement.offsetHeight);");
        log.info("Total page height: {}", totalHeight);

        // Get viewport height
        Long viewportHeight = (Long) ((JavascriptExecutor) driver).executeScript(
                "return Math.max(document.documentElement.clientHeight, window.innerHeight);");
        log.info("Viewport height: {}", viewportHeight);

        // Scroll and stitch screenshots
        Long currentPosition = 0L;
        int index = 0;

        while (currentPosition < totalHeight) {
            log.debug("Scrolling to position: {}", currentPosition);

            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, " + currentPosition + ");");

            try {
                Thread.sleep(500); // Wait for scroll
            } catch (InterruptedException e) {
                log.error("Interrupted during scroll wait at position: {}", currentPosition, e);
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }

            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            log.debug("Captured screenshot chunk index: {}", index);

            try {
                BufferedImage image = ImageIO.read(screenshot);
                log.debug("Successfully read screenshot chunk index: {}", index);
            } catch (IOException e) {
                log.error("Error reading screenshot chunk at index: {}", index, e);
                throw new RuntimeException(e);
            }

            currentPosition += viewportHeight;
            index++;
        }

        log.info("Completed scrolling. Total chunks captured: {}", index);

        // Final screenshot save (simplified approach)
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File(filePath));
            log.info("Final screenshot saved successfully at: {}", filePath);
        } catch (IOException e) {
            log.error("Failed to save final screenshot to: {}", filePath, e);
            throw new RuntimeException(e);
        }

        log.info("Full page screenshot capture completed.");
    }
}
