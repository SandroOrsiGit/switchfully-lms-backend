package com.switchfully.switchfullylmsbackend.dtos.codelabs;

public class CreateCodelabDto {
    private String name;
    private Long moduleId;

    public CreateCodelabDto() {
    }

    public CreateCodelabDto(String name, Long moduleId) {
        this.name = name;
        this.moduleId = moduleId;
    }

    public String getName() {
        return name;
    }

    public Long getModuleId() {
        return moduleId;
    }
}
