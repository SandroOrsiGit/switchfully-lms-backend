package com.switchfully.switchfullylmsbackend.dto;

public class CoachDto {
    private Long id;
    private String email;
    private String displayName;

    public CoachDto() {
    }

    public CoachDto(Long id, String email, String displayName) {
        this.id = id;
        this.email = email;
        this.displayName = displayName;
    }

    //---Getters-----------
    public Long getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }
    public String getDisplayName() {
        return displayName;
    }
}
