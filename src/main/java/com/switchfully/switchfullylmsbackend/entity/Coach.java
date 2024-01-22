package com.switchfully.switchfullylmsbackend.entity;

import jakarta.persistence.Entity;

@Entity
public class Coach extends AbstractUser {
    public Coach( String email, String displayName) {
        super(email, displayName);
    }

    public Coach() {

    }
}
