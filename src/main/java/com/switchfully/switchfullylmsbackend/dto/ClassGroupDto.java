package com.switchfully.switchfullylmsbackend.dto;

import com.switchfully.switchfullylmsbackend.entity.Course;

import java.util.List;

public class ClassGroupDto {
    private Long id;
    private String name;
    private CourseDto courseDto;
    private List<CoachDto> coachDtoList;
    private List<StudentDto> studentDtoList;

    public ClassGroupDto(Long id, String name, CourseDto courseDto, List<CoachDto> coachDtoList, List<StudentDto> studentDtoList) {
        this.id = id;
        this.name = name;
        this.courseDto = courseDto;
        this.coachDtoList = coachDtoList;
        this.studentDtoList = studentDtoList;
    }

//    ---Getters---------------
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public CourseDto getCourseDto() {
        return courseDto;
    }
    public List<CoachDto> getCoachDtoList() {
        return coachDtoList;
    }
    public List<StudentDto> getStudentDtoList() {
        return studentDtoList;
    }
}
