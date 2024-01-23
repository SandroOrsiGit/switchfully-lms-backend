package com.switchfully.switchfullylmsbackend.entities;

import jakarta.persistence.Entity;

@Entity
public class Coach extends AbstractUser {
    public Coach( String email, String displayName) {
        super(email, displayName);
    }

    public Coach() {

    }

    @Override
    public String toString() {
        return "Coach{} " + super.toString();
    }
}
