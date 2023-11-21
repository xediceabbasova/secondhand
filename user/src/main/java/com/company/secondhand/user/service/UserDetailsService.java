package com.company.secondhand.user.service;

import com.company.secondhand.user.dto.*;
import com.company.secondhand.user.dto.converter.UserDetailsDtoConverter;
import com.company.secondhand.user.dto.request.CreateUserDetailsRequest;
import com.company.secondhand.user.dto.request.UpdateUserDetailsRequest;
import com.company.secondhand.user.exception.UserDetailsNotFoundException;
import com.company.secondhand.user.model.PhoneNumber;
import com.company.secondhand.user.model.PhoneNumberType;
import com.company.secondhand.user.model.UserDetails;
import com.company.secondhand.user.model.Users;
import com.company.secondhand.user.repository.UserDetailsRepository;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserDetailsService {
    private final UserDetailsRepository userDetailsRepository;
    private final UserDetailsDtoConverter converter;
    private final UserService userService;

    public UserDetailsService(UserDetailsRepository userDetailsRepository, UserDetailsDtoConverter converter, UserService userService) {
        this.userDetailsRepository = userDetailsRepository;
        this.converter = converter;
        this.userService = userService;
    }

    public UserDetailsDto createUserDetails(final CreateUserDetailsRequest request) {
        Users user = userService.findUserById(request.userId());

        UserDetails userDetails = new UserDetails(
                request.phoneNumber()
                        .stream()
                        .map(p -> new PhoneNumber(p.countryCode(), p.phoneNumber(), PhoneNumberType.getByValue(p.phoneNumberType().getValue())))
                        .collect(Collectors.toSet()),
                request.address(),
                request.city(),
                request.country(),
                request.postCode(),
                user);

        return converter.convert(userDetailsRepository.save(userDetails));
    }

    public UserDetailsDto updateUserDetails(final Long userDetailsId, final UpdateUserDetailsRequest request) {
        UserDetails userDetails = findUserDetailsById(userDetailsId);

        UserDetails updateUserDetails = new UserDetails(
                userDetails.getId(),
                request.phoneNumber()
                        .stream()
                        .map(p -> new PhoneNumber(p.countryCode(), p.phoneNumber(), PhoneNumberType.getByValue(p.phoneNumberType().getValue())))
                        .collect(Collectors.toSet()),
                request.address(),
                request.city(),
                request.country(),
                request.postCode(),
                userDetails.getUsers());

        return converter.convert(userDetailsRepository.save(updateUserDetails));
    }

    public void deleteUserDetails(final Long id) {
        findUserDetailsById(id);
        userDetailsRepository.deleteById(id);
    }

    private UserDetails findUserDetailsById(final Long userDetailsId) {
        return userDetailsRepository.findById(userDetailsId)
                .orElseThrow(() -> new UserDetailsNotFoundException("User details couldn't be found by following id:" + userDetailsId));
    }

}
