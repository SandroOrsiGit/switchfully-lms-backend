package com.switchfully.switchfullylmsbackend.dtos.modules;

public class AbstractModuleDto {
    private final Long id;
    private final String name;

    public AbstractModuleDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
