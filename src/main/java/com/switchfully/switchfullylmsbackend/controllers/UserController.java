package com.switchfully.switchfullylmsbackend.controllers;

import com.switchfully.switchfullylmsbackend.dtos.users.CreateStudentDto;
import com.switchfully.switchfullylmsbackend.dtos.users.StudentDto;
import com.switchfully.switchfullylmsbackend.dtos.users.UserDto;
import com.switchfully.switchfullylmsbackend.dtos.users.UpdateUserDto;
import com.switchfully.switchfullylmsbackend.security.KeycloakService;
import com.switchfully.switchfullylmsbackend.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    private final KeycloakService keycloakService;
    private final UserService userService;

    public UserController(KeycloakService keycloakService, UserService userService) {
        this.keycloakService = keycloakService;
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.CREATED)
	@PostMapping(consumes = "application/json", produces = "application/json", path = "/register")
    public StudentDto createStudent(@RequestBody CreateStudentDto createStudentDto) {
        keycloakService.addUser(createStudentDto);

        return userService.createStudent(createStudentDto);
    }

    @GetMapping()
    public UserDto getUserByToken(@RequestHeader("Authorization") String bearerToken) {
        return userService.getUserDtoByToken(bearerToken);
    }
    
    @PostMapping(path = "/validate-token")
    @ResponseStatus(HttpStatus.OK)
    public Boolean validateToken(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        return userService.validateToken(token);
    }
    
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(consumes = "application/json", produces = "application/json")
    @PreAuthorize("hasAnyAuthority('coach', 'student')")
    public void updateUser(@RequestHeader("Authorization") String bearerToken, @RequestBody UpdateUserDto updateUserDto) {
        // TODO check if user is the authenticated user
        // TODO return UserDto so the front end can update it's user data
        userService.updateUser(updateUserDto);
    }

}
