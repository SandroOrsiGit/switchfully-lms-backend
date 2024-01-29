package com.switchfully.switchfullylmsbackend.dtos.courses;

public class CreateCourseDto {
    private String name;

    public CreateCourseDto() {
    }

    public CreateCourseDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
