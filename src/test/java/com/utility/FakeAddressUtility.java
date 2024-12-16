package com.utility;

import com.github.javafaker.Faker;
import com.ui.pojo.AddressPOJO;

import java.util.Locale;

public class FakeAddressUtility {

    public static AddressPOJO getFakeAddress() {
        Faker faker = new Faker(Locale.US);

        AddressPOJO addressPojo =

                new AddressPOJO(faker.company().name(),
                        faker.address().buildingNumber(),
                        faker.address().streetAddress(),
                        faker.address().city(),
                        faker.numerify("#####"),
                        faker.phoneNumber().cellPhone(),
                        faker.phoneNumber().cellPhone(),
                        "other",
                        "office address"+faker.numerify("##"),
                        faker.address().state());

        return addressPojo;
    }

}
