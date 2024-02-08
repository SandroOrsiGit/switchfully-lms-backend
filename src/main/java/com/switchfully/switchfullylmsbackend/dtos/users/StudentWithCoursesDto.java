package com.switchfully.switchfullylmsbackend.dtos.users;

import com.switchfully.switchfullylmsbackend.dtos.courses.CourseDto;

import java.util.List;

public class StudentWithCoursesDto {
    private Long id;
    private String email;
    private String displayName;
    private List<CourseDto> courseDtoList;

    public StudentWithCoursesDto() {
    }

    public StudentWithCoursesDto(Long id, String email, String displayName, List<CourseDto> courseDtoList) {
        this.id = id;
        this.email = email;
        this.displayName = displayName;
        this.courseDtoList = courseDtoList;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getDisplayName() {
        return displayName;
    }

    public List<CourseDto> getCourseDtoList() {
        return courseDtoList;
    }
}
