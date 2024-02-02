package com.switchfully.switchfullylmsbackend.dtos.comments;

import java.time.*;

public class CommentDto {
    private Long id;
    private String text;
    private String studentName;

    private LocalDate createdDate;

    public CommentDto() {
    }

    public CommentDto(Long id, String text, String studentName, LocalDate createdDate) {
        this.id = id;
        this.text = text;
        this.studentName = studentName;
        this.createdDate = createdDate;
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

    public LocalDate getCreatedDate() {
        return createdDate;
    }
}
