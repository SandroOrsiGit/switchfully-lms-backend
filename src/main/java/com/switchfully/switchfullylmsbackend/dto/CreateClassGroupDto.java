package com.switchfully.switchfullylmsbackend.dto;

public class CreateClassGroupDto {
    private String name;

    public CreateClassGroupDto() {
    }
    public CreateClassGroupDto(String name) {
        this.name = name;
    }


    // --- Getters ---------------------
    public String getName() {
        return name;
    }
}
