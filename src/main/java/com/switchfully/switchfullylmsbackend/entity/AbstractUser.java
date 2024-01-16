package com.switchfully.switchfullylmsbackend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public abstract class AbstractUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "display_name")
    private String displayName;
}
