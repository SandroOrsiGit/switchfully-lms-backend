package com.switchfully.switchfullylmsbackend.dtos.comments;

import com.switchfully.switchfullylmsbackend.dtos.users.StudentDto;

public class CommentDto {
    private Long id;
    private String text;
    private StudentDto studentDto;

    public CommentDto() {
    }

    public CommentDto(Long id, String text, StudentDto studentDto) {
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
    public StudentDto getStudentDto() {
        return studentDto;
    }
}
