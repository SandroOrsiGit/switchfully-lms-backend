package com.switchfully.switchfullylmsbackend.dtos.codelabs;

public class CodelabNoCommentDto {

    private Long id;
    private String name;

    public CodelabNoCommentDto() {
    }

    public CodelabNoCommentDto(Long id, String name) {
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
