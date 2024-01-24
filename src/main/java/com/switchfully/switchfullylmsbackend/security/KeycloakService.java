package com.switchfully.switchfullylmsbackend.security;

import com.switchfully.switchfullylmsbackend.dtos.users.CreateUserDto;
import org.springframework.stereotype.Service;

@Service
public interface KeycloakService {

    void addUser(CreateUserDto createUserDto);

}
