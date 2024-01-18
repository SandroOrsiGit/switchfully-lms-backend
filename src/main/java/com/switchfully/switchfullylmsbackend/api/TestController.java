package com.switchfully.switchfullylmsbackend.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "test")
@CrossOrigin(origins = "http://localhost:4200")
public class TestController {
    
    @GetMapping(produces = "application/json")
    public String getHelloWorldUnauthorized() {
        return "Hello world, not authorized";
    }

    @GetMapping(produces = "application/json", value = "student")
    @PreAuthorize("hasAuthority('student')")
    public String getHelloWorldStudent() {
        return "Hello world, authorized as student";
    }


    @GetMapping(produces = "application/json", value = "coach")
    @PreAuthorize("hasAuthority('coach')")
    public String getHelloWorldCoach() {
        return "Hello world, authorized as coach";
    }

}
