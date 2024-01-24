package com.switchfully.switchfullylmsbackend.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "codelabs")
public class Codelab {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @OneToMany
    @JoinColumn(name = "codelab_id")
    private List<Comment> comments;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Comment> getComments() {
        return comments;
    }
}
