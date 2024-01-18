package com.switchfully.switchfullylmsbackend.controller;

import com.switchfully.switchfullylmsbackend.dto.user.CreateUserDto;
import com.switchfully.switchfullylmsbackend.dto.user.UpdateUserDto;
import com.switchfully.switchfullylmsbackend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(consumes = "application/json", produces = "application/json", path = "/register")
    public void registerUser(@RequestBody CreateUserDto createUserDto) {
        userService.addUser(createUserDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping(consumes = "application/json", produces = "application/json", path = "/update")
    public void updateUser(@RequestBody UpdateUserDto updateUserDto) {
        userService.updateUser(updateUserDto);
    }

}
