package com.switchfully.switchfullylmsbackend.dto;

public class ProgressDto {
    private Long id;
    private String name;

    public ProgressDto() {
    }

    public ProgressDto(Long id, String name) {
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
