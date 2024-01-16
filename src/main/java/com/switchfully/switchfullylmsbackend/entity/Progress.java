package com.switchfully.switchfullylmsbackend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "progresses")
public class Progress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
}
