package com.switchfully.switchfullylmsbackend.entities;

import jakarta.persistence.*;

import java.time.*;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TEXT", name = "text")
    private String text;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Student student;
    @Column(name = "created_date")
    private LocalDate createdDate;

    public Comment() {
    }

    public Comment(String text, Student student, LocalDate createdDate) {
        this.text = text;
        this.student = student;
        this.createdDate = createdDate;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Student getStudent() {
        return student;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }
}
