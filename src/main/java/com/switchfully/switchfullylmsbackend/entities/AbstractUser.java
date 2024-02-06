package com.switchfully.switchfullylmsbackend.entities;

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
    @Column(name="keycloak_id")
    private String keycloakId;

    public AbstractUser(String email, String displayName, String keycloakId) {
        this.email = email;
        this.displayName = displayName;
        this.keycloakId = keycloakId;
    }

    public AbstractUser() {

    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getKeycloakId() {
        return keycloakId;
    }

    public void setKeycloakId(String keycloakId) {
        this.keycloakId = keycloakId;
    }

    public abstract String getRole();
}
