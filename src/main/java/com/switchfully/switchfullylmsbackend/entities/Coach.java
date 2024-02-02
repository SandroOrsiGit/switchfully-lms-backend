package com.switchfully.switchfullylmsbackend.entities;

import jakarta.persistence.Entity;

@Entity
public class Coach extends AbstractUser {
    public Coach( String email, String displayName, String keycloakId) {
        super(email, displayName, keycloakId);
    }

    public Coach() {

    }

    @Override
    public String getRole() {
        return "coach";
    }

    @Override
    public String toString() {
        return "Coach{} " + super.toString();
    }
}
