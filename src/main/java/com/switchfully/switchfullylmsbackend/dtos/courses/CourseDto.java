package com.switchfully.switchfullylmsbackend.dtos.courses;

public class CourseDto {
    private Long id;
    private String name;

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
