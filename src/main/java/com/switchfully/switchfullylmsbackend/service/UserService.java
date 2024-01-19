package com.switchfully.switchfullylmsbackend.service;

import com.switchfully.switchfullylmsbackend.dto.user.CreateUserDto;
import com.switchfully.switchfullylmsbackend.dto.user.UpdateUserDto;
import com.switchfully.switchfullylmsbackend.entity.AbstractUser;
import com.switchfully.switchfullylmsbackend.entity.Student;
import com.switchfully.switchfullylmsbackend.mapper.StudentMapper;
import com.switchfully.switchfullylmsbackend.repository.UserRepository;
import com.switchfully.switchfullylmsbackend.security.KeycloakService;
import org.springframework.stereotype.Service;

import java.util.Objects;

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
        userRepository.save(student);
    }

    public void updateUser(UpdateUserDto updateUserDto) {
        AbstractUser emailUser = userRepository.findByEmail(updateUserDto.getEmail());
        if (emailUser != null && !Objects.equals(emailUser.getId(), updateUserDto.getId())) {
            throw new IllegalArgumentException("email already in use");
        }
        AbstractUser user = userRepository.findById(updateUserDto.getId()).orElseThrow(() -> new IllegalArgumentException("User not found"));
        user.setDisplayName(updateUserDto.getDisplayName());
        userRepository.save(user);
    }

}
