package com.company.secondhand.user.controller;

import com.company.secondhand.user.dto.request.CreateUserRequest;
import com.company.secondhand.user.dto.request.UpdateUserRequest;
import com.company.secondhand.user.dto.UserDto;
import com.company.secondhand.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {

        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{mail}")
    public ResponseEntity<UserDto> getUserByMail(@PathVariable String mail) {
        return ResponseEntity.ok(userService.getUserByMail(mail));
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody CreateUserRequest userRequest) {
        return ResponseEntity.ok(userService.createUser(userRequest));
    }

    @GetMapping("/exist/{id}")
    public ResponseEntity<Boolean> isUserIdExist(@PathVariable Long id) {
        return ResponseEntity.ok(userService.isUserIdExist(id));
    }

    @PutMapping("/{mail}")
    public ResponseEntity<UserDto> updateUser(
            @PathVariable String mail,
            @RequestBody UpdateUserRequest updateUserRequest) {
        return ResponseEntity.ok(userService.updateUser(mail, updateUserRequest));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> deactivateUser(@PathVariable("id") Long id) {
        userService.deactivateUser(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/active")
    public ResponseEntity<Void> activeUser(@PathVariable("id") Long id) {
        userService.activateUser(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }


}
