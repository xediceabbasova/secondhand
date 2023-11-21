package com.company.secondhand.user.dto.request;

public record CreateUserRequest(String mail,
                                String firstName,
                                String lastName,
                                String middleName) {
}
