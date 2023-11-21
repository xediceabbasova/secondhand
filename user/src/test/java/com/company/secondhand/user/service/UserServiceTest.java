package com.company.secondhand.user.service;

import com.company.secondhand.user.TestSupport;
import com.company.secondhand.user.dto.request.UpdateUserRequest;
import com.company.secondhand.user.dto.converter.UserDtoConverter;
import com.company.secondhand.user.exception.UserIsNotActiveException;
import com.company.secondhand.user.exception.UserNotFoundException;
import com.company.secondhand.user.model.Users;
import com.company.secondhand.user.repository.UsersRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class UserServiceTest extends TestSupport {

    private UserDtoConverter converter;
    private UsersRepository repository;
    private UserService userService;

    //@BeforeAll butun test caseler baslamadan evvel 1 defe calisir

    @BeforeEach//setup funksiyasi her test case de calisacak
    public void setUp() {
        converter = mock(UserDtoConverter.class);
        repository = mock(UsersRepository.class);
        userService = new UserService(repository, converter);
    }

//    @Test
//    public void testGetAllUsers_itShouldReturnUserDtoList() {
//        List<Users> userList = generateUsers();
//        List<UserDto> userDtoList = generateUserDtoList(userList);
//
//        when(repository.findAll()).thenReturn(userList);
//        when(converter.convert(userList)).thenReturn(userDtoList);
//
//        List<UserDto> result = userService.getAllUsers();
//
//        assertEquals(userDtoList, result);
//        verify(repository).findAll();
//        verify(converter).convert(userList);
//
//    }

//    @Test
//    public void testGetUserByMail_whenUserMailExist_itShouldReturnUserDto() {
//        String mail = "mail@khadija.net";
//        Users user = generateUser(mail);
//        UserDto userDto = generateUserDto(mail);
//
//        when(repository.findByMail(mail)).thenReturn(Optional.of(user));
//        when(converter.convert(user)).thenReturn(userDto);
//
//        UserDto result = userService.getUserByMail(mail);
//
//        assertEquals(userDto, result);
//        verify(repository).findByMail(mail);
//        verify(converter).convert(user);
//
//    }

    @Test
    public void testGetUserByMail_whenUserMailDoesNotExist_itShouldThrowUserNotFoundException() {
        String mail = "mail@khadija.net";

        when(repository.findByMail(mail)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class,
                () -> userService.getUserByMail(mail)
        );

        verify(repository).findByMail(mail);
        verifyNoInteractions(converter);

    }

//    @Test
//    public void testCreateUser_itShouldReturnCreatedUserDto() {
//        String mail = "mail@khadija.net";
//        CreateUserRequest request = new CreateUserRequest(mail, "firstName", "lastName", "");
//        Users user = new Users(mail, "firstName", "lastName", "", false);
//        Users savedUser = new Users(1L, mail, "firstName", "lastName", "", false);
//        UserDto userDto = new UserDto(mail, "firstName", "lastName", "");
//
//        when(repository.save(user)).thenReturn(savedUser);
//        when(converter.convert(savedUser)).thenReturn(userDto);
//
//        UserDto result = userService.createUser(request);
//
//        assertEquals(userDto, result);
//
//        verify(repository).save(user);
//        verify(converter).convert(savedUser);
//
//    }

//    @Test
//    public void testUpdateUser_whenUserMailExistAndUserIsActive_itShouldReturnUpdatedUserDto() {
//        String mail = "mail@khadija.net";
//        UpdateUserRequest request = new UpdateUserRequest("firstName2", "lastName2", "");
//        Users user = new Users(1L, mail, "firstName", "lastName", "", true);
//        Users updateUser = new Users(1L, mail, "firstName2", "lastName2", "", true);
//        Users savedUser = new Users(1L, mail, "firstName2", "lastName2", "", true);
//        UserDto userDto = new UserDto(mail, "firstName2", "lastName2", "");
//
//        when(repository.findByMail(mail)).thenReturn(Optional.of(user));
//        when(repository.save(updateUser)).thenReturn(savedUser);
//        when(converter.convert(savedUser)).thenReturn(userDto);
//
//        UserDto result = userService.updateUser(mail, request);
//
//        assertEquals(userDto, result);
//
//        verify(repository).findByMail(mail);
//        verify(repository).save(updateUser);
//        verify(converter).convert(savedUser);
//
//    }

    @Test
    public void testUpdateUser_whenUserMailDoesNotExist_itShouldThrowUserNotFoundException() {
        String mail = "mail@khadija.net";
        UpdateUserRequest request = new UpdateUserRequest("firstName2", "lastName2", "");

        when(repository.findByMail(mail)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () ->
                userService.updateUser(mail, request)
        );

        verify(repository).findByMail(mail);
        verifyNoMoreInteractions(repository);
        verifyNoInteractions(converter);

    }

    @Test
    public void testUpdateUser_whenUserMailExistButUserIsNotActive_itShouldThrowUserNotActiveException() {
        String mail = "mail@khadija.net";
        UpdateUserRequest request = new UpdateUserRequest("firstName2", "lastName2", "");
        Users user = new Users(1L, mail, "firstName", "lastName", "", false);

        when(repository.findByMail(mail)).thenReturn(Optional.of(user));

        assertThrows(UserIsNotActiveException.class, () ->
                userService.updateUser(mail, request)
        );

        verify(repository).findByMail(mail);
        verifyNoMoreInteractions(repository);
        verifyNoInteractions(converter);

    }

    @Test
    public void testDeactivateUser_whenUserIdExist_itShouldUpdateUserByActiveFalse() {
        String mail = "mail@khadija.net";
        Users user = new Users(userId, mail, "firstName", "lastName", "", true);
        Users savedUser = new Users(userId, mail, "firstName", "lastName", "", false);

        when(repository.findById(userId)).thenReturn(Optional.of(user));

        userService.deactivateUser(userId);

        verify(repository).findById(userId);
        verify(repository).save(savedUser);

    }

    @Test
    public void testDeactivateUser_whenUserIdDoesNotExist_itShouldThrowUserNotFoundException() {

        when(repository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () ->
                userService.deactivateUser(userId)
        );

        verify(repository).findById(userId);
        verifyNoMoreInteractions(repository);

    }

    @Test
    public void testActivateUser_whenUserIdExist_itShouldUpdateUserByActiveTrue() {
        String mail = "mail@khadija.net";
        Users user = new Users(userId, mail, "firstName", "lastName", "", false);
        Users savedUser = new Users(userId, mail, "firstName", "lastName", "", true);

        when(repository.findById(userId)).thenReturn(Optional.of(user));

        userService.activateUser(userId);

        verify(repository).findById(userId);
        verify(repository).save(savedUser);

    }

    @Test
    public void testActivateUser_whenUserIdDoesNotExist_itShouldThrowUserNotFoundException() {

        when(repository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () ->
                userService.activateUser(userId)
        );

        verify(repository).findById(userId);
        verifyNoMoreInteractions(repository);

    }

    @Test
    public void testDeleteUser_whenUserIdExist_itShouldDeleteUser() {
        String mail = "mail@khadija.net";
        Users user = new Users(userId, mail, "firstName", "lastName", "", false);

        when(repository.findById(userId)).thenReturn(Optional.of(user));

        userService.deleteUser(userId);

        verify(repository).findById(userId);
        verify(repository).deleteById(userId);

    }

    @Test
    public void testDeleteUser_whenUserIdDoesNotExist_itShouldThrowUserNotFoundException() {

        when(repository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () ->
                userService.deleteUser(userId)
        );

        verify(repository).findById(userId);
        verifyNoMoreInteractions(repository);

    }


}