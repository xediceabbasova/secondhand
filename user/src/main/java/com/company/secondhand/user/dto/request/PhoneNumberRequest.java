package com.company.secondhand.user.dto.request;

import com.company.secondhand.user.dto.PhoneNumberType;

public record PhoneNumberRequest(
        String countryCode,
        String phoneNumber,
        PhoneNumberType phoneNumberType
) {
}
