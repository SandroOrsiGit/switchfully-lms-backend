package com.switchfully.switchfullylmsbackend.dtos.modules;

import com.switchfully.switchfullylmsbackend.dtos.codelabs.CodelabNoCommentDto;

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
