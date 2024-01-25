package com.switchfully.switchfullylmsbackend.mappers;

import com.switchfully.switchfullylmsbackend.dtos.codelabprogresses.CodelabProgressDto;
import com.switchfully.switchfullylmsbackend.dtos.users.StudentDto;
import com.switchfully.switchfullylmsbackend.dtos.users.CreateUserDto;
import com.switchfully.switchfullylmsbackend.dtos.users.StudentNoCodelabProgressDto;
import com.switchfully.switchfullylmsbackend.entities.Student;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StudentMapper {

    private final CodelabProgressMapper codelabProgressMapper;

    public StudentMapper(CodelabProgressMapper codelabProgressMapper) {
        this.codelabProgressMapper = codelabProgressMapper;
    }
    public StudentDto mapStudentToStudentDto(Student student) {
        return new StudentDto(
                student.getId(),
                student.getEmail(),
                student.getDisplayName(),
                mapCodelabProgressListToCodelabProgressListDto(student)
        );
    }

    public StudentNoCodelabProgressDto mapStudentToStudentNoCodelabProgressDto(Student student) {
        return new StudentNoCodelabProgressDto(
        student.getId(),
        student.getEmail(),
        student.getDisplayName()
        );
    }

    public Student mapCreateUserDtoToStudent(CreateUserDto createUserDto) {
        return new Student(
                createUserDto.getEmail(),
                createUserDto.getDisplayName()
        );
    }

    private List<CodelabProgressDto> mapCodelabProgressListToCodelabProgressListDto(Student student) {
        return student.getCodelabProgresses().stream()
                .map(codelabProgressMapper::mapCodelabProgressToCodelabProgressDto)
                .collect(Collectors.toList());
    }
}
