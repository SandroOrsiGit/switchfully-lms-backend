package com.switchfully.switchfullylmsbackend.dtos.comments;

import com.switchfully.switchfullylmsbackend.dtos.users.StudentDto;

public class CommentDto {
    private Long id;
    private String text;
    //TODO should we rename studentDto to studentName? Check CommentMapper for info
    private String studentDto;

    public CommentDto() {
    }

    public CommentDto(Long id, String text, String studentDto) {
        this.id = id;
        this.text = text;
        this.studentDto = studentDto;
    }

    //    ---Getters---------------
    public Long getId() {
        return id;
    }
    public String getText() {
        return text;
    }
    public String getStudentDto() {
        return studentDto;
    }
}
