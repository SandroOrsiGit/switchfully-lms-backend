package com.switchfully.switchfullylmsbackend.mappers;

import com.switchfully.switchfullylmsbackend.dtos.users.StudentDto;
import com.switchfully.switchfullylmsbackend.dtos.users.CreateUserDto;
import com.switchfully.switchfullylmsbackend.entities.Student;
import org.springframework.stereotype.Component;
@Component
public class StudentMapper {
    public StudentDto mapStudentToStudentDto(Student student) {
        return new StudentDto(
                student.getId(),
                student.getEmail(),
                student.getDisplayName(),
                student.
        )
    }

    public Student mapCreateUserDtoToStudent(CreateUserDto createUserDto) {
        return new Student(
                createUserDto.getEmail(),
                createUserDto.getDisplayName()
        );
    }
}
