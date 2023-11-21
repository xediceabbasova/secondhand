package com.company.secondhand.user.dto.converter;

import com.company.secondhand.user.dto.PhoneNumberDto;
import com.company.secondhand.user.model.PhoneNumber;
import com.company.secondhand.user.dto.PhoneNumberType;
import org.springframework.stereotype.Component;

@Component
public class PhoneNumberDtoConverter {

    public PhoneNumberDto convert(PhoneNumber from) {

        return new PhoneNumberDto(
                from.getCountryCode(),
                from.getPhoneNumber(),
                PhoneNumberType.fromInt(from.getPhoneNumberType().getValue()));
    }
}
