package com.switchfully.switchfullylmsbackend.dto;

public class CodelabProgressDto {
    private Long id;
    private CodelabDto codelab;
    private ProgressDto progress;

    public CodelabProgressDto() {
    }
    public CodelabProgressDto(Long id, CodelabDto codelab, ProgressDto progress) {
        this.id = id;
        this.codelab = codelab;
        this.progress = progress;
    }


    //    ---Getters---------------
    public Long getId() {
        return id;
    }
    public CodelabDto getCodelab() {
        return codelab;
    }
    public ProgressDto getProgress() {
        return progress;
    }
}
