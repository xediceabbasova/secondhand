package com.company.secondhand.user.dto;

import java.util.List;

public record UserDetailsDto(List<PhoneNumberDto> phoneNumber,
                             String address,
                             String city,
                             String country,
                             String postCode) {
}
