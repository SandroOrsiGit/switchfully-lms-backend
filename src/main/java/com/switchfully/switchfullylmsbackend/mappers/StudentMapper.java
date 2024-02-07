package com.switchfully.switchfullylmsbackend.mappers;

import com.switchfully.switchfullylmsbackend.dtos.codelabprogresses.CodelabProgressDto;
import com.switchfully.switchfullylmsbackend.dtos.users.StudentDto;
import com.switchfully.switchfullylmsbackend.dtos.users.CreateStudentDto;
import com.switchfully.switchfullylmsbackend.dtos.users.StudentDto;
import com.switchfully.switchfullylmsbackend.entities.Student;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class StudentMapper {

    public StudentDto mapStudentToStudentDto(Student student) {
        return new StudentDto(
            student.getId(),
            student.getEmail(),
            student.getDisplayName()
        );
    }

    public Student mapCreateUserDtoToStudent(CreateStudentDto createStudentDto, String createdUserId) {
        return new Student(
                createStudentDto.getEmail(),
                createStudentDto.getDisplayName(),
                createdUserId
        );
    }
}
