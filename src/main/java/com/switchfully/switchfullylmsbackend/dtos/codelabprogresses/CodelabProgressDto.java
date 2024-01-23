package com.switchfully.switchfullylmsbackend.dtos.codelabprogresses;

import com.switchfully.switchfullylmsbackend.dtos.codelabs.CodelabNoCommentDto;
import com.switchfully.switchfullylmsbackend.dtos.progresses.ProgressDto;
import com.switchfully.switchfullylmsbackend.dtos.codelabs.CodelabDto;

public class CodelabProgressDto {
    private Long id;
    private CodelabNoCommentDto codelab;
    private ProgressDto progress;

    public CodelabProgressDto() {
    }
    public CodelabProgressDto(Long id, CodelabNoCommentDto codelab, ProgressDto progress) {
        this.id = id;
        this.codelab = codelab;
        this.progress = progress;
    }


    //    ---Getters---------------
    public Long getId() {
        return id;
    }
    public CodelabNoCommentDto getCodelab() {
        return codelab;
    }
    public ProgressDto getProgress() {
        return progress;
    }
}
