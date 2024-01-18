package com.switchfully.switchfullylmsbackend.mapper;

import com.switchfully.switchfullylmsbackend.dto.StudentDto;
import com.switchfully.switchfullylmsbackend.dto.user.CreateUserDto;
import com.switchfully.switchfullylmsbackend.entity.Student;
import org.springframework.stereotype.Component;
@Component
public class StudentMapper {
    public StudentDto mapStudentToStudentDto(Student student) {
        return null;
    }

    public Student mapCreateUserDtoToStudent(CreateUserDto createUserDto) {
        return new Student(
                createUserDto.getEmail(),
                createUserDto.getDisplayName()
        );
    }
}
