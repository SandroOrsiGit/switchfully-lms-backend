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
    @Column(name = "display_name")
    private String displayName;

    public AbstractUser(String email, String displayName) {
        this.email = email;
        this.displayName = displayName;
    }

    public AbstractUser() {

    }
}
