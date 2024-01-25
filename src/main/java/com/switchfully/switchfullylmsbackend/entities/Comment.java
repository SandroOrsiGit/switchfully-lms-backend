package com.switchfully.switchfullylmsbackend.entities;

import jakarta.persistence.*;

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

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Student getStudent() {
        return student;
    }
}
