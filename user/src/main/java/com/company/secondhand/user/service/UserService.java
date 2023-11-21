package com.company.secondhand.user.service;

import com.company.secondhand.user.dto.request.CreateUserRequest;
import com.company.secondhand.user.dto.request.UpdateUserRequest;
import com.company.secondhand.user.dto.UserDto;
import com.company.secondhand.user.dto.converter.UserDtoConverter;
import com.company.secondhand.user.exception.UserIsNotActiveException;
import com.company.secondhand.user.exception.UserNotFoundException;
import com.company.secondhand.user.model.Users;
import com.company.secondhand.user.repository.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UsersRepository userRepository;
    private final UserDtoConverter userDtoConverter;

    public UserService(UsersRepository userInformationRepository, UserDtoConverter userDtoConverter) {
        this.userRepository = userInformationRepository;
        this.userDtoConverter = userDtoConverter;
    }

    public List<UserDto> getAllUsers() {
        return userDtoConverter.convert(userRepository.findAll());
    }

    public UserDto getUserByMail(String mail) {
        Users userInformation = findUserByMail(mail);
        return userDtoConverter.convert(userInformation);
    }


    public UserDto createUser(final CreateUserRequest userRequest) {

        Users userInformation = new Users(
                userRequest.mail(),
                userRequest.firstName(),
                userRequest.lastName(),
                userRequest.middleName(),
                false);

        return userDtoConverter.convert(userRepository.save(userInformation));

    }

    public UserDto updateUser(final String mail, final UpdateUserRequest updateUserRequest) {
        Users userInformation = findUserByMail(mail);

        if (!userInformation.getActive()) {
            logger.warn(String.format("The user wanted update is not active!, user mail: %s", mail));
            throw new UserIsNotActiveException();
        }


        Users updatedUserInformation = new Users(
                userInformation.getId(),
                userInformation.getMail(),
                updateUserRequest.firstName(),
                updateUserRequest.lastName(),
                updateUserRequest.middleName(),
                userInformation.getActive());
        return userDtoConverter.convert(userRepository.save(updatedUserInformation));
    }


    public void deactivateUser(final Long id) {

        changeActivateUser(id, false);
    }

    public void activateUser(final Long id) {
        changeActivateUser(id, true);
    }

    public void deleteUser(final Long id) {
        findUserById(id);

        userRepository.deleteById(id);

    }

    private void changeActivateUser(final Long id, boolean isActive) {
        Users userInformation = findUserById(id);
        Users updatedUserInformation = new Users(
                userInformation.getId(),
                userInformation.getMail(),
                userInformation.getFirstName(),
                userInformation.getLastName(),
                userInformation.getMiddleName(),
                isActive);
        userRepository.save(updatedUserInformation);

    }

    protected Users findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User couldn't be found by following id:" + id));

    }

    private Users findUserByMail(final String mail) {
        return userRepository.findByMail(mail)
                .orElseThrow(() -> new UserNotFoundException("User couldn't be found by following mail:" + mail));

    }

    public Boolean isUserIdExist(Long id) {
        return userRepository.existsById(id);
    }
}
