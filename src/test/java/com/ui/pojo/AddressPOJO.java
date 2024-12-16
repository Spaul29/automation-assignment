package com.ui.pojo;

import lombok.Getter;

@Getter
public class AddressPOJO {

    private final String company;
    private final String addressLine1;
    private final String addressLine2;
    private final String city;
    private final String postCode;
    private final String homePhoneNumber;
    private final String mobileNumber;
    private final String otherInformation;
    private final String addressAlias;
    private final String state;

    public AddressPOJO(String company, String addressLine1, String addressLine2, String city, String postCode,
                       String homePhoneNumber, String mobileNumber, String otherInformation, String addressAlias, String state) {
        super();
        this.company = company;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.postCode = postCode;
        this.homePhoneNumber = homePhoneNumber;
        this.mobileNumber = mobileNumber;
        this.otherInformation = otherInformation;
        this.addressAlias = addressAlias;
        this.state = state;
    }

    @Override
    public String toString() {
        return "AddressPOJO [company=" + company + ", addressLine1=" + addressLine1 + ", addressLine2=" + addressLine2
                + ", city=" + city + ", postCode=" + postCode + ", homePhoneNumber=" + homePhoneNumber
                + ", mobileNumber=" + mobileNumber + ", otherInformation=" + otherInformation + ", addressAlias="
                + addressAlias + ", state=" + state + "]";
    }
}
