package com.company.secondhand.user.dto;

public enum PhoneNumberType {
    PRIVATE(1), BUSINESS(2);

    private final int value;


    PhoneNumberType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static PhoneNumberType fromInt(int value) {
        for (PhoneNumberType type : PhoneNumberType.values()) {
            if (type.value == value) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid PhoneNumberType value: " + value);
    }
}
