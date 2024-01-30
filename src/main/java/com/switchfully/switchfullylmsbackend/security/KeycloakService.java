package com.switchfully.switchfullylmsbackend.security;

import com.switchfully.switchfullylmsbackend.dtos.users.CreateStudentDto;
import org.springframework.stereotype.Service;

@Service
public interface KeycloakService {

    void addUser(CreateStudentDto createStudentDto);

}
