package com.switchfully.switchfullylmsbackend.controller;

import com.switchfully.switchfullylmsbackend.dto.user.CreateUserDto;
import com.switchfully.switchfullylmsbackend.dto.user.UserDto;
import com.switchfully.switchfullylmsbackend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/register")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "application/json", produces = "application/json")
    public void registerUser(@RequestBody CreateUserDto createUserDto) {
        userService.addUser(createUserDto);
    }

    @GetMapping()
    public UserDto getUserByToken(@RequestHeader("Authorization") String bearerToken){
        return userService.getUserByToken(bearerToken);
    }
}
