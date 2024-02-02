package com.switchfully.switchfullylmsbackend.dtos.comments;

public class CommentDto {
    private Long id;
    private String text;
    private String studentName;

    public CommentDto() {
    }

    public CommentDto(Long id, String text, String studentName) {
        this.id = id;
        this.text = text;
        this.studentName = studentName;
    }

    //    ---Getters---------------
    public Long getId() {
        return id;
    }
    public String getText() {
        return text;
    }
    public String getStudentName() {
        return studentName;
    }
}
