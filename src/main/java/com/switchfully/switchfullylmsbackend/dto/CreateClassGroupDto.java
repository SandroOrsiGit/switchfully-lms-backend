package com.switchfully.switchfullylmsbackend.dto;

public class CreateClassGroupDto {
    private final String name;

    public CreateClassGroupDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
