package com.switchfully.switchfullylmsbackend.controllers;

import com.switchfully.switchfullylmsbackend.dtos.users.*;
import com.switchfully.switchfullylmsbackend.entities.*;
import com.switchfully.switchfullylmsbackend.security.KeycloakService;
import com.switchfully.switchfullylmsbackend.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
@CrossOrigin(origins = {"http://localhost:4200", "https://switchfully-lms.netlify.app"})
public class UserController {
    private final KeycloakService keycloakService;
    private final UserService userService;

    public UserController(KeycloakService keycloakService, UserService userService) {
        this.keycloakService = keycloakService;
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.CREATED)
	@PostMapping(path = "/register", consumes = "application/json", produces = "application/json")
    public StudentDto createStudent(@RequestBody CreateStudentDto createStudentDto) {
        String createdUserId = keycloakService.addUser(createStudentDto);

        return userService.createStudent(createStudentDto, createdUserId);
    }

    @GetMapping()
    public UserDto getUserByToken(@RequestHeader("Authorization") String bearerToken) {
        return userService.getUserDtoByToken(bearerToken);
    }
    
    @GetMapping(path = "/validate-token")
    @ResponseStatus(HttpStatus.OK)
    public Boolean validateToken(@RequestHeader("Authorization") String bearerToken) {
        String token = bearerToken.replace("Bearer ", "");
        return userService.validateToken(token);
    }
    
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(consumes = "application/json", produces = "application/json")
    @PreAuthorize("hasAnyAuthority('coach', 'student')")
    public UserDto updateUser(@RequestHeader("Authorization") String bearerToken, @RequestBody UpdateUserDto updateUserDto) {
        AbstractUser abstractUser = userService.getUserByToken(bearerToken);
        return userService.updateUser(abstractUser, updateUserDto);

    }

    @GetMapping(path= "/students")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('coach')")
    public List<StudentDto> getAllStudents() {
        return userService.getAllStudents();
    }

    @GetMapping(path = "/students/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('coach')")
    public StudentWithCoursesDto getStudentById(@PathVariable Long id) {
        return userService.getStudentWithCourseById(id);
    }

}
