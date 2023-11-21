package com.company.secondhand.user.dto.request;

import java.util.List;

public record UpdateUserDetailsRequest(
        List<PhoneNumberRequest> phoneNumber,
        String address,
        String city,
        String country,
        String postCode
) {
}
