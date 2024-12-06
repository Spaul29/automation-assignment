package com.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class LambdaTestUtility {
    private static final String hubURL = "https://hub.lambdatest.com/wd/hub";
    private static final ThreadLocal<WebDriver> driverLocal = new ThreadLocal<>();
    private static final ThreadLocal<DesiredCapabilities> capabilitiesLocal = new ThreadLocal<>();

    public static WebDriver initializeLambdaTestSession(String browserName, String testName) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", browserName);
        capabilities.setCapability("browserVersion", "127");
        Map<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("username", "paulsarbani39");
        ltOptions.put("accessKey", "bKEvHrCxnjwDrSJ0AbaLw7vLFGO7gfaMg5BeDY0ve87faaB4Gl");
        ltOptions.put("build", "Selenium 4");
        ltOptions.put("name", testName);
        ltOptions.put("platformName", "Windows 10");
        ltOptions.put("seCdp", true);
        ltOptions.put("selenium_version", "4.23.0");
        capabilities.setCapability("LT:Options", ltOptions);
        capabilitiesLocal.set(capabilities);
        WebDriver driver = null;

        try {
            URI hubURI = new URI(hubURL);
            driver = new RemoteWebDriver(hubURI.toURL(), capabilitiesLocal.get());
        } catch (MalformedURLException|URISyntaxException e) {
            e.printStackTrace();
        }
        driverLocal.set(driver);

        return driverLocal.get();
    }

    public static void quitSession() {
        if (driverLocal.get() != null) {
            driverLocal.get().quit();
        }
    }
}
