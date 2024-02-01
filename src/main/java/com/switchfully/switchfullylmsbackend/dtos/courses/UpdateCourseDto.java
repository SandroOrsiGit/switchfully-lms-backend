package com.switchfully.switchfullylmsbackend.dtos.courses;

public class UpdateCourseDto {
    private String name;

    public UpdateCourseDto() {
    }

    public UpdateCourseDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
