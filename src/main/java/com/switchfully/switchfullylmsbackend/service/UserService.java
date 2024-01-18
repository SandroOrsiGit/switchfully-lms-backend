package com.switchfully.switchfullylmsbackend.service;

import com.switchfully.switchfullylmsbackend.dto.user.CreateUserDto;
import com.switchfully.switchfullylmsbackend.entity.Student;
import com.switchfully.switchfullylmsbackend.mapper.StudentMapper;
import com.switchfully.switchfullylmsbackend.repository.UserRepository;
import com.switchfully.switchfullylmsbackend.security.KeycloakService;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final StudentMapper studentMapper;
    private final UserRepository userRepository;
    private final KeycloakService keycloakService;

    public UserService(StudentMapper studentMapper, UserRepository userRepository, KeycloakService keycloakService) {
        this.studentMapper = studentMapper;
        this.userRepository = userRepository;
        this.keycloakService = keycloakService;
    }

    public void addUser(CreateUserDto createUserDto) {
        Student student = studentMapper.mapCreateUserDtoToStudent(createUserDto);
        keycloakService.addUser(createUserDto);
        System.out.println("keycloakuser added: "+createUserDto);
        userRepository.save(student);
    }
}
