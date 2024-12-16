package com.ui.pages;

import com.constants.Browser;
import static com.constants.Env.*;
import com.utility.BrowserUtility;
import static com.utility.PropertiesUtil.*;

import com.utility.LoggerUtility;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public final class HomePage extends BrowserUtility {

    Logger logger = LoggerUtility.getLogger(this.getClass());
    private static final By SIGN_IN_LINK_LOCATOR = By.xpath("//a[contains(text(),\"Sign in\")]");

    public HomePage(Browser browserName, boolean isHeadless) {
        super(browserName,isHeadless);
        goToWebsite(getPropertyValue(QA,"URL"));
        maximizeWindow();
    }

    public HomePage(WebDriver driver) {
        super(driver);
        goToWebsite(getPropertyValue(QA,"URL"));
        maximizeWindow();
    }

    public LoginPage goToLoginPage() {
        logger.info("Go to Sign In Page");
        clickOn(SIGN_IN_LINK_LOCATOR);
        return new LoginPage(getDriver());
    }

}
