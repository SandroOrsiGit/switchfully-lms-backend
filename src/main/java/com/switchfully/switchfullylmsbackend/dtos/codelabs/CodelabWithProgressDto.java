package com.switchfully.switchfullylmsbackend.dtos.codelabs;

import com.switchfully.switchfullylmsbackend.dtos.progresses.ProgressDto;

public class CodelabWithProgressDto {
    private Long id;
    private String name;
    private ProgressDto progress;

    public CodelabWithProgressDto(Long id, String name, ProgressDto progress) {
        this.id = id;
        this.name = name;
        this.progress = progress;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ProgressDto getProgress() {
        return progress;
    }
}
