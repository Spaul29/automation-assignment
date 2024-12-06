package com.utility;

import com.constants.Browser;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class BrowserUtility {

    Logger logger = LoggerUtility.getLogger(this.getClass());

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    protected WebDriver getDriver() {
        return driver.get();
    }

    protected void setDriver(WebDriver driver) {
        BrowserUtility.driver.set(driver);
    }

    protected BrowserUtility(WebDriver driver) {
        BrowserUtility.driver.set(driver);
    }

    protected BrowserUtility(Browser browserName) {
        logger.info("Launching Browser : {}", browserName);
        if (browserName == Browser.CHROME) {
            driver.set(new ChromeDriver());
        } else if (browserName == Browser.FIREFOX) {
            driver.set(new FirefoxDriver());
        } else {
            logger.error("Browser Name is not supported");
            System.err.println("Browser Name is not supported");
        }
    }

    protected BrowserUtility(Browser browserName, boolean isHeadless) {
        logger.info("Launching Browser : {}", browserName);
        if (browserName == Browser.CHROME) {
            if (isHeadless) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless=old");
                options.addArguments("--window-size=1920,1080");
                driver.set(new ChromeDriver(options));
            }
            else {
                driver.set(new ChromeDriver());
            }
        } else if (browserName == Browser.FIREFOX) {
            if (isHeadless) {
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--headless=old");
                driver.set(new FirefoxDriver(options));
            }
            else {
                driver.set(new FirefoxDriver());
            }
        } else {
            logger.error("Browser Name is not supported");
            System.err.println("Browser Name is not supported");
        }
    }

    protected void goToWebsite(String url) {
        logger.info("Go to {}", url);
        driver.get().get(url);
    }

    protected void maximizeWindow() {
        logger.info("Maximizing the browser window");
        driver.get().manage().window().maximize();
    }

    protected void clickOn(By locator) {
        logger.info("Finding element with the locator {}", locator);
        WebElement element = driver.get().findElement(locator);
        logger.info("Element found and performing click");
        element.click();
    }

    protected void enterText(By locator, String textToEnter) {
        logger.info("Finding element with the locator {}", locator);
        WebElement element = driver.get().findElement(locator);
        logger.info("Element found and entering {}", textToEnter);
        element.sendKeys(textToEnter);
    }

    protected String getVisibleText(By locator) {
        logger.info("Finding element with the locator {}", locator);
        WebElement element = driver.get().findElement(locator);
        logger.info("Element found and returning the visible text {}", element.getText());
        return element.getText();
    }

    public String takeScreenshot(String name) {
        TakesScreenshot screenshot = ((TakesScreenshot) driver.get());
        File screenshotData = screenshot.getScreenshotAs(OutputType.FILE);

        // Ensure directory exists
        String dirPath = System.getProperty("user.dir") + "/screenshots/";
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // Construct file path
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        String timestamp = formatter.format(date);
        String path = dirPath + name + "_" + timestamp + ".png";

        try {
            File screenshotFile = new File(path);
            FileUtils.copyFile(screenshotData, screenshotFile);
            return path;
        } catch (IOException e) {
            logger.error("Failed to save screenshot: {}", e.getMessage());
            return null;
        }
    }

    public void quit() {
        driver.get().quit();
    }
}
