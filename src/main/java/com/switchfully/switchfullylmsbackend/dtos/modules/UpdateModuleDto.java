package com.switchfully.switchfullylmsbackend.dtos.modules;

import java.util.List;

public class UpdateModuleDto {
    private String name;
    private List<Long> courseIds;

    public UpdateModuleDto() {
    }

    public UpdateModuleDto(String name, List<Long> courseIds) {
        this.name = name;
        this.courseIds = courseIds;
    }

    public String getName() {
        return name;
    }

    public List<Long> getCourseIds() {
        return courseIds;
    }
}
