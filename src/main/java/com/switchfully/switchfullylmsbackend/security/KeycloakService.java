package com.switchfully.switchfullylmsbackend.security;

import com.switchfully.switchfullylmsbackend.dtos.users.*;
import com.switchfully.switchfullylmsbackend.entities.*;
import org.springframework.stereotype.Service;

@Service
public interface KeycloakService {

    String addUser(CreateStudentDto createStudentDto);

}
