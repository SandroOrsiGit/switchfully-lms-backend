package com.switchfully.switchfullylmsbackend.dtos.modules;

import com.switchfully.switchfullylmsbackend.dtos.codelabs.CodelabNoCommentDto;
import com.switchfully.switchfullylmsbackend.dtos.courses.CourseDto;
import com.switchfully.switchfullylmsbackend.entities.SubModule;

import java.util.List;

public class SubModuleDto {
    private final Long id;
    private final String name;
    private final List<CodelabNoCommentDto> codelabs;

    public SubModuleDto(Long id, String name, List<CodelabNoCommentDto> codelabs) {
        this.id = id;
        this.name = name;
        this.codelabs = codelabs;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<CodelabNoCommentDto> getCodelabs() {
        return codelabs;
    }
}
