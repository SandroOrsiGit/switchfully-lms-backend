package com.switchfully.switchfullylmsbackend.dtos.codelabs;

public class CreateCodelabDto {
    private String name;

    public CreateCodelabDto() {
    }

    public CreateCodelabDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
