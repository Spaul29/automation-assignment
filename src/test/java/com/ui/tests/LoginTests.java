package com.ui.tests;

import static org.testng.Assert.*;

import com.ui.pojo.User;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({com.ui.listeners.TestListener.class})
public final class LoginTests extends TestBase {

   /* @Test(description = "Verifies if valid user is able to login into the application", groups = {"e2e","sanity"},
                        dataProviderClass = com.ui.dataproviders.LoginDataProvider.class, dataProvider = "LoginTestDataProvider")
    public void validLoginTest(User user) {
        assertEquals(homePage.goToLogin().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName(),
                    "Jatin Sharma","Username mismatch");
    }*/

    @Test(description = "Verifies if valid user is able to login into the application", groups = {"e2e","sanity"},
            dataProviderClass = com.ui.dataproviders.LoginDataProvider.class, dataProvider = "LoginCSVTestDataProvider", retryAnalyzer = com.ui.listeners.MyRetryAnalyzer.class)
    public void validLoginTestWithCSV(User user) {
        assertEquals(homePage.goToLogin().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName(),
                "Jatin Sharma","Username mismatch");
    }

  /*  @Test(description = "Verifies if valid user is able to login into the application", groups = {"e2e","sanity"},
            dataProviderClass = com.ui.dataproviders.LoginDataProvider.class, dataProvider = "LoginExcelTestDataProvider", retryAnalyzer = com.ui.listeners.MyRetryAnalyzer.class)
    public void validLoginTestWithExcel(User user) throws InterruptedException {
        assertEquals(homePage.goToLogin().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName(),
                "Jatin Sharma","Username mismatch");
    } */

}
