package com.switchfully.switchfullylmsbackend.dtos.codelabs;

public class UpdateCodelabDto {

    private Long codelabId;
    private String name;
    private Long moduleId;

    public UpdateCodelabDto() {}

    public UpdateCodelabDto(Long codelabId, String name, Long moduleId) {
        this.codelabId = codelabId;
        this.name = name;
        this.moduleId = moduleId;
    }

    public Long getCodelabId() {
        return codelabId;
    }

    public void setCodelabId(Long codelabId) {
        this.codelabId = codelabId;
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
