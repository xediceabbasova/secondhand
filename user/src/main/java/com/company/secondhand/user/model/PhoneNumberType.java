package com.company.secondhand.user.model;

public enum PhoneNumberType {
    PRIVATE(1), BUSINESS(2);


    private int value;

    PhoneNumberType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static PhoneNumberType getByValue(int value) {
        return value == 1 ? PRIVATE : BUSINESS;
    }
}
