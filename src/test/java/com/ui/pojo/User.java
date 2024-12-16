package com.ui.pojo;

import lombok.Getter;

@Getter
public class User {

    private final String emailAddress;
    private final String password;

    public User(String emailAddress, String password) {
        this.emailAddress = emailAddress;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "emailAddress='" + emailAddress + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
