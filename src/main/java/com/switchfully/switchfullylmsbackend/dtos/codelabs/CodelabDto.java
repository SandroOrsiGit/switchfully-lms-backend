package com.switchfully.switchfullylmsbackend.dtos.codelabs;

import com.switchfully.switchfullylmsbackend.dtos.comments.CommentDto;

import java.util.List;

public class CodelabDto {
    private Long id;
    private String name;
    private List<CommentDto> commentDtoList;

    public CodelabDto() {
    }
    public CodelabDto(Long id, String name, List<CommentDto> commentDtoList) {
        this.id = id;
        this.name = name;
        this.commentDtoList = commentDtoList;
    }

    //    ---Getters---------------
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public List<CommentDto> getCommentDtoList() {
        return commentDtoList;
    }
}
