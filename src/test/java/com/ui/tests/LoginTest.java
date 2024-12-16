package com.ui.tests;

import static org.testng.Assert.*;

import com.ui.pojo.User;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({com.ui.listeners.TestListener.class})
public final class LoginTest extends TestBase {
    private static final String INVALID_EMAIL_ADDRESS = "test@gmail.com";
    private static final String INVALID_PASSWORD = "Qwerty1234!";

   @Test(description = "Verify if the proper error message is shown for the user when they enter invalid credentials",
           groups = {"e2e","sanity"})
    public void InvalidCredsLoginTest() {
        assertEquals(homePage.goToLoginPage().doLoginWithInvalidCredentials(INVALID_EMAIL_ADDRESS, INVALID_PASSWORD).getErrorMessage(),
                    "Authentication failed.","Error message mismatch");
    }

    @Test(description = "Verifies if valid user is able to login into the application", groups = {"e2e","sanity"},
            dataProviderClass = com.ui.dataproviders.LoginDataProvider.class, dataProvider = "LoginCSVTestDataProvider", retryAnalyzer = com.ui.listeners.MyRetryAnalyzer.class)
    public void validLoginTestWithCSV(User user) {
        assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName(),
                "Jatin Sharma","Username mismatch");
    }

  /*  @Test(description = "Verifies if valid user is able to login into the application", groups = {"e2e","sanity"},
            dataProviderClass = com.ui.dataproviders.LoginDataProvider.class, dataProvider = "LoginExcelTestDataProvider", retryAnalyzer = com.ui.listeners.MyRetryAnalyzer.class)
    public void validLoginTestWithExcel(User user) throws InterruptedException {
        assertEquals(homePage.goToLogin().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName(),
                "Jatin Sharma","Username mismatch");
    } */

}
