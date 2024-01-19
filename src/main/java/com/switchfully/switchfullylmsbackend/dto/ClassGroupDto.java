package com.switchfully.switchfullylmsbackend.dto;

import com.switchfully.switchfullylmsbackend.entity.Course;
import jakarta.persistence.Column;

import java.time.LocalDate;
import java.util.List;

public class ClassGroupDto {
    private Long id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private CourseDto courseDto;
    private List<CoachDto> coachDtoList;
    private List<StudentDto> studentDtoList;

    public ClassGroupDto() {
    }

    public ClassGroupDto(Long id, String name, LocalDate startDate, LocalDate endDate, CourseDto courseDto, List<CoachDto> coachDtoList, List<StudentDto> studentDtoList) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
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
