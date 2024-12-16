package com.ui.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ui.pages.MyAccountPage;
import com.ui.pojo.AddressPOJO;
import com.utility.FakeAddressUtility;

import java.util.List;

public class AddNewFirstAddressTest extends TestBase {

    private MyAccountPage myAccountPage;
    private AddressPOJO address;

    @BeforeMethod(description = "Valid First Time user logs into the application")
    public void setup() {
        myAccountPage = homePage.goToLoginPage().doLoginWith("bakomam596@skrak.com", "password");
        address = FakeAddressUtility.getFakeAddress();
    }

    @Test
    public void addNewAddress() {
        boolean isSavedAddressDisplayed = myAccountPage.goToAddAddressPage().saveAddress(address);
        Assert.assertTrue(isSavedAddressDisplayed);
    }

}