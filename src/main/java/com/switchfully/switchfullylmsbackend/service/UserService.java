package com.switchfully.switchfullylmsbackend.service;

import com.switchfully.switchfullylmsbackend.dto.user.CreateUserDto;
import com.switchfully.switchfullylmsbackend.entity.Student;
import com.switchfully.switchfullylmsbackend.mapper.StudentMapper;
import com.switchfully.switchfullylmsbackend.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final StudentMapper studentMapper;
    private final UserRepository userRepository;
    public UserService(StudentMapper studentMapper, UserRepository userRepository) {
        this.studentMapper = studentMapper;
        this.userRepository = userRepository;
    }


    public void addUser(CreateUserDto createUserDto) {
        Student student = studentMapper.mapCreateUserDtoToStudent(createUserDto);
        userRepository.save(student);
    }
}
