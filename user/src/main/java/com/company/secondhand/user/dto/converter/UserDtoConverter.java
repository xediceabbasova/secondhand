package com.company.secondhand.user.dto.converter;

import com.company.secondhand.user.dto.UserDto;
import com.company.secondhand.user.dto.converter.UserDetailsDtoConverter;
import com.company.secondhand.user.model.Users;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDtoConverter {

    private final UserDetailsDtoConverter converter;

    public UserDtoConverter(UserDetailsDtoConverter converter) {
        this.converter = converter;
    }

    public UserDto convert(Users from) {
        return new UserDto(from.getMail(),
                from.getFirstName(),
                from.getLastName(),
                from.getMiddleName(),
                converter.convert(new ArrayList<>(from.getUserDetailsSet())));
    }

    public List<UserDto> convert(List<Users> fromList) {
        return fromList.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
