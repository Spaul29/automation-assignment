package com.ui.pages;

import com.ui.pojo.AddressPOJO;
import com.utility.BrowserUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class AddressPage extends BrowserUtility {
    private static final By COMPANY_TEXTBOX_LOCATOR = By.id("company");
    private static final By ADDRESS1_TEXTBOX_LOCATOR = By.id("address1");
    private static final By ADDRESS2_TEXTBOX_LOCATOR = By.id("address2");
    private static final By CITY_TEXTBOX_LOCATOR = By.id("city");
    private static final By POST_CODE_TEXTBOX_LOCATOR = By.id("postcode");
    private static final By HOME_PHONE_TEXTBOX_LOCATOR = By.id("phone");
    private static final By MOBILE_NUMBER_TEXTBOX_LOCATOR = By.id("phone_mobile");
    private static final By OTHER_INFORMATION_TEXTAREA_LOCATOR = By.id("other");
    private static final By ADDRESS_ALIAS_TEXTBOX_LOCATOR = By.id("alias");
    private static final By STATE_DROPDOWN_LOCATOR = By.id("id_state");
    private static final By SAVE_ADDRESS_BUTTON_LOCATOR = By.id("submitAddress");
    private static final By ADDRESS_HEADING = By.xpath("//h3[@class=\"page-subheading\"]");
    private static final By ADD_NEW_ADDRESS_BUTTON_LOCATOR = By.xpath("//a[@title='Add an address']/span");

    public AddressPage(WebDriver driver) {
        super(driver);
    }

    public boolean saveAddress(AddressPOJO addressPOJO) {
        clickOnAddNewAddressButton();
        enterText(COMPANY_TEXTBOX_LOCATOR, addressPOJO.getCompany());
        enterText(ADDRESS1_TEXTBOX_LOCATOR, addressPOJO.getAddressLine1());
        enterText(ADDRESS2_TEXTBOX_LOCATOR, addressPOJO.getAddressLine2());
        enterText(CITY_TEXTBOX_LOCATOR, addressPOJO.getCity());
        enterText(POST_CODE_TEXTBOX_LOCATOR, addressPOJO.getPostCode());
        enterText(HOME_PHONE_TEXTBOX_LOCATOR, addressPOJO.getHomePhoneNumber());
        enterText(MOBILE_NUMBER_TEXTBOX_LOCATOR, addressPOJO.getMobileNumber());
        enterText(OTHER_INFORMATION_TEXTAREA_LOCATOR, addressPOJO.getOtherInformation());
        clearText(ADDRESS_ALIAS_TEXTBOX_LOCATOR);
        enterText(ADDRESS_ALIAS_TEXTBOX_LOCATOR, addressPOJO.getAddressAlias());
        selectFromDropDown(STATE_DROPDOWN_LOCATOR, addressPOJO.getState());
        clickOn(SAVE_ADDRESS_BUTTON_LOCATOR);
        List<String> newAddress = getAllVisibleText(ADDRESS_HEADING);

        return isAddressSaved(newAddress,addressPOJO.getAddressAlias());
    }

    public void clickOnAddNewAddressButton() {
        clickOn(ADD_NEW_ADDRESS_BUTTON_LOCATOR);
    }

    public boolean isAddressSaved(List<String> addedAddresslist, String searchAliasAddress) {
        System.out.println("Address list : "+ addedAddresslist);
        System.out.println("Search Address : "+ searchAliasAddress);
        return addedAddresslist.stream().anyMatch(address -> address.toLowerCase().equals(searchAliasAddress));
    }
}