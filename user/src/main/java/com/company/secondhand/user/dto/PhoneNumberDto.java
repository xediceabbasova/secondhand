package com.company.secondhand.user.dto;

public record PhoneNumberDto(
        String countryCode,
        String phoneNumber,
        PhoneNumberType phoneNumberType
) {
}
