package com.switchfully.switchfullylmsbackend.security;

import com.switchfully.switchfullylmsbackend.dtos.users.*;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("test")
public class TestKeycloakService implements KeycloakService{

    @Override
    public String addUser(CreateStudentDto createStudentDto) {
        return null;
    }

}
