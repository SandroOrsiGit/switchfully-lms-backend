package com.switchfully.switchfullylmsbackend.dto;

public class CourseDto {
    private Long id;
    private String name;

    public CourseDto() {
    }
    public CourseDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    //    ---Getters---------------
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
}
