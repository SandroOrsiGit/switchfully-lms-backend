package com.switchfully.switchfullylmsbackend.security;

import com.switchfully.switchfullylmsbackend.dtos.users.CreateStudentDto;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("test")
public class TestKeycloakService implements KeycloakService{

    @Override
    public void addUser(CreateStudentDto createStudentDto) {

    }

}
