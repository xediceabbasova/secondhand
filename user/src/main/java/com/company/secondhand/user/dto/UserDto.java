package com.company.secondhand.user.dto;

import java.util.List;

public record UserDto(String mail,
                      String firstName,
                      String lastName,
                      String middleName,
                      List<UserDetailsDto> userDetails) {
}
