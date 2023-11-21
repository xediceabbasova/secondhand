package com.company.secondhand.user.dto.converter;

import com.company.secondhand.user.dto.UserDetailsDto;
import com.company.secondhand.user.model.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDetailsDtoConverter {

    private final PhoneNumberDtoConverter phoneNumberDtoConverter;

    public UserDetailsDtoConverter(PhoneNumberDtoConverter phoneNumberDtoConverter) {
        this.phoneNumberDtoConverter = phoneNumberDtoConverter;
    }

    public UserDetailsDto convert(UserDetails from) {
        return new UserDetailsDto(
                from.getPhoneNumbers().stream().map(phoneNumberDtoConverter::convert).collect(Collectors.toList()),
                from.getAddress(),
                from.getCity(),
                from.getCountry(),
                from.getPostCode());
    }

    public List<UserDetailsDto> convert(List<UserDetails> from) {
        return from.stream()
                .map(f -> convert(f))
                .collect(Collectors.toList());
    }

}
