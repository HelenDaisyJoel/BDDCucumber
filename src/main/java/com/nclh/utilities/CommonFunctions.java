package com.nclh.utilities;

import com.nclh.constants.GlobalConstants;
import lombok.extern.log4j.Log4j2;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.util.LoadLibs;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
@Log4j2
public class CommonFunctions {



    public static boolean clearFolder(File folder) {
        boolean isSuccess = true;

        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    isSuccess = clearFolder(file); // Recursively clear subfolders
                }
                if (!file.delete()) {
                    isSuccess = false;
                    System.out.println("Failed to delete: " + file.getAbsolutePath());
                }
            }
        }
        return isSuccess;
    }

    public char getRandomCharacter() {
        Random r = new Random();
        char c = (char) (r.nextInt(26) + 'a');
        return c;
    }

    public String readToastMessage(WebElement element) {
        String scrShotDir = "testData";
        File scrShotDirPath = new File("./" + scrShotDir + "//");
        String imgName = null;
        try {
            imgName = SeleniumActions.takeScreenShotOfElement(element);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String result = null;
        File imageFile = new File(scrShotDirPath, imgName);
        System.out.println("Image name is :" + imageFile.toString());
        ITesseract instance = new Tesseract();
        File tessDataFolder = LoadLibs.extractTessResources("tessdata");
        instance.setDatapath(tessDataFolder.getAbsolutePath());

        try {
            Thread.sleep(5000);
            result = instance.doOCR(imageFile);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("Image text = {" + result + "}");
        return result;
    }

    public String getDateIncrementedBy(int days, String dateFormatType) {
        DateFormat dateFormat = new SimpleDateFormat(dateFormatType);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, days);
        String convertedDate = dateFormat.format(cal.getTime());
        return convertedDate;
    }

    public String timeStampConverter(String time24) throws ParseException {
        // Create a SimpleDateFormat object for 24-hour format (HH:mm:ss)
        SimpleDateFormat sdf24 = new SimpleDateFormat("HH:mm:ss");

        // Parse the 24-hour time string (e.g., "17:38:38") into a Date object
        Date date = sdf24.parse(time24);

        // Create a SimpleDateFormat object for 12-hour format (hh:mm:ss a)
        SimpleDateFormat sdf12 = new SimpleDateFormat("hh:mm:ss a");

        // Convert to 12-hour format
        String formattedDate = sdf12.format(date);

        if (formattedDate.charAt(0) == '0') {
            formattedDate = formattedDate.substring(1);
        }

        // Print and return the 12-hour formatted time
        System.out.println("12-hour format: " + formattedDate);
        System.out.println(formattedDate.substring(0,5));
        return formattedDate.substring(0,5);
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public int randomNumber() {
        Random objGenerator = new Random();
        int randomNumber = objGenerator.nextInt(9999);
        System.out.println("Random No : " + randomNumber);
        return randomNumber;
    }

    public int randomNumber(int maxValue) {
        Random objGenerator = new Random();
        int randomNumber = objGenerator.nextInt(maxValue);
        System.out.println("Random No : " + randomNumber);
        return randomNumber;
    }

    public String getPdfContent(String url) {
        String stripText = null;
        try {
            URL pdfURL = new URL(url);
            InputStream is = pdfURL.openStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            PDDocument doc = PDDocument.load(bis.readAllBytes());
            //PDDocument doc = Loader.loadPDF(bis.readAllBytes());
            int pages = doc.getNumberOfPages();
            System.out.println("The total number of pages " + pages);
            PDFTextStripper strip = new PDFTextStripper();
            strip.setStartPage(1);
            strip.setEndPage(2);
            stripText = strip.getText(doc);
            System.out.println(stripText);
            doc.close();
        } catch (IOException ioException) {
            System.out.println(ioException.toString());
        }
        return stripText;
    }

    public int parseCount(String value) {
        return Integer.parseInt(value.replaceAll(",", "").trim());
    }

    public static int extractNumberFromText(String text, String regexPattern) {

        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }

        throw new RuntimeException("Unable to extract number from text: " + text);
    }

    public  void closeErrorPopups(WebDriver driver) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.SHORT_WAIT));
            // Locate all ERROR popups
            List<WebElement> errorPopups = driver.findElements(
                    By.xpath("//div[@id='toast-title-error' and contains(text(),'ERROR')]")
            );

            if (!errorPopups.isEmpty()) {
                for (WebElement popup : errorPopups) {

                    // Wait until visible
                    wait.until(ExpectedConditions.visibilityOf(popup));
                    log.info("Error popup detected: {}", popup.getText());


                    // Try to find and click close button (adjust locator as needed)
                    try {
                        WebElement closeBtn = popup.findElement(
                                By.xpath("./ancestor::div[contains(@class,'toast')]//button")
                        );
                        SeleniumActions seleniumActions = new SeleniumActions(driver);
                        seleniumActions.jsClick(closeBtn);
//                        closeBtn.click();
                        log.info("Popup closed.");
                    } catch (NoSuchElementException e) {
                        log.info("Close button not found for popup.");
                    }
                }
            }

        } catch (Exception e) {
            log.info("No error popup present or already handled.");
        }
    }

    public void closeToastIfPresent(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.SHORT_WAIT));

        try {
            List<WebElement> closeButtons = wait.until(
                    ExpectedConditions.presenceOfAllElementsLocatedBy(
                            By.xpath("//button[contains(@class,'mat-icon-button')]")
                    )
            );

            for (WebElement btn : closeButtons) {
                if (btn.isDisplayed()) {
                    try {
                        btn.click();
                    } catch (Exception e) {
                        // fallback JS click
                        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
                    }
                }
            }

            // Optional: wait until toast disappears
            wait.until(ExpectedConditions.invisibilityOfAllElements(closeButtons));

        } catch (TimeoutException e) {
            // No toast found → ignore
        }
    }

    /**
     * Clicks an anchor (<a>) element using its visible text.
     * <p>
     * Waits until the element is clickable before performing the click.
     * This method is useful when the link text is dynamic and supplied at runtime.
     * </p>
     *
     * Example:
     * <pre>
     * clickLinkByText(driver, "Checked In Ashore");
     * clickLinkByText(driver, "Checked Out");
     * </pre>
     *
     * @param driver   WebDriver instance.
     * @param linkText Visible text of the link to click.
     * @throws org.openqa.selenium.TimeoutException
     *         if the link is not clickable within the wait duration.
     */
    public void clickLinkByText(WebDriver driver, String linkText) {
        String xpath = String.format("//a[normalize-space()='%s']", linkText);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.MINI_WAIT));

        wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath(xpath))
        ).click();
    }

}
