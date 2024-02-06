package com.switchfully.switchfullylmsbackend.dtos.codelabs;

public class UpdateCodelabDto {

    private String name;
    private Long moduleId;

    public UpdateCodelabDto() {}

    public UpdateCodelabDto(String name, Long moduleId) {
        this.name = name;
        this.moduleId = moduleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getModuleId() {
        return moduleId;
    }

    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
    }

}
