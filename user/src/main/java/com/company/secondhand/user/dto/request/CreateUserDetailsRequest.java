package com.company.secondhand.user.dto.request;

import java.util.List;

public record CreateUserDetailsRequest(
        List<PhoneNumberRequest> phoneNumber,
        String address,
        String city,
        String country,
        String postCode,
        Long userId
) {
}