package com.nclh.managers;

import com.nclh.enums.DriverType;
import com.nclh.enums.EnvironmentType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

public class AllDriverManager {

    protected  WebDriver webDriver;
    private static DriverType driverType;
    public static EnvironmentType environmentType;
    protected static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

    public AllDriverManager() {
        driverType = FileReaderManager.getInstance().getConfigFileReader().getBrowser();
        environmentType = FileReaderManager.getInstance().getConfigFileReader().getEnvironment();
    }

    private WebDriver createLocalDriver() {
        switch (driverType) {
            case CHROME:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("force-device-scale-factor=0.75");
                chromeOptions.addArguments("high-dpi-support=0.75");
                chromeOptions.setAcceptInsecureCerts(true);
                HashMap<String, String> map = new HashMap<>();
                //map.put("download.default_directory", System.getProperty("user.dir")+"/downloads");
                map.put("download.default_directory",System.getProperty("user.dir")+"\\downloads");
                chromeOptions.setExperimentalOption("prefs", map);
                webDriver = new ChromeDriver(chromeOptions);
                break;
            case FIREFOX:
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--headless");
                webDriver = new FirefoxDriver(firefoxOptions);
                break;
            case EDGE:
                webDriver = new EdgeDriver();
                break;
            case SAFARI:
                webDriver = new SafariDriver();
                break;
        }
        long time = FileReaderManager.getInstance().getConfigFileReader().getTime();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(time));
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
        //webDriver.manage().timeouts().setScriptTimeout(Duration.ofSeconds(time));
        return webDriver;
    }

    private WebDriver createRemoteDriver() {
        WebDriver webDriver = null;
        switch (driverType) {
            case CHROME:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("force-device-scale-factor=0.75");
                chromeOptions.addArguments("high-dpi-support=0.75");
                chromeOptions.addArguments("start-maximized");
                chromeOptions.addArguments("--disable-infobars");
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-notifications");
                try {
                    webDriver = new RemoteWebDriver(new URL(System.getProperty("gridUrl")+"/wd/hub"), chromeOptions);
                } catch (MalformedURLException e) {
                    String url = FileReaderManager.getInstance().getConfigFileReader().getGridUrl();
                    System.out.println("url :: " + url);
                    try {
                        webDriver = new RemoteWebDriver(new URL(url+"wd/hub"), chromeOptions);
                    } catch (MalformedURLException ex) {
                        throw new RuntimeException(ex);
                    }

                }
                break;
            case FIREFOX:
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("start-maximized");
                firefoxOptions.addArguments("--disable-infobars");
                firefoxOptions.addArguments("--no-sandbox");
                firefoxOptions.addArguments("--disable-notifications");
                try {
                    webDriver = new RemoteWebDriver(new URL(System.getProperty("gridUrl")), firefoxOptions);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case EDGE:
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("start-maximized");
                edgeOptions.addArguments("--disable-infobars");
                edgeOptions.addArguments("--no-sandbox");
                edgeOptions.addArguments("--disable-notifications");
                try {
                    webDriver = new RemoteWebDriver(new URL(System.getProperty("gridUrl")), edgeOptions);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
                break;
        }
        return webDriver;
    }

    public void createDriver() {
        switch (environmentType) {
            case LOCAL:
                webDriver = createLocalDriver();
                threadLocalDriver.set(webDriver);
                break;
            case REMOTE:
                webDriver = createRemoteDriver();
                threadLocalDriver.set(webDriver);
                break;
        }
    }


    public WebDriver getDriver() {
        return threadLocalDriver.get();
    }

    public void closeDriver() {
        webDriver.quit();
    }
}
