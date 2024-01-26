package com.switchfully.switchfullylmsbackend.dtos.users;

public class StudentNoCodelabProgressDto {
    private Long id;
    private String email;
    private String displayName;

    public StudentNoCodelabProgressDto() {
    }

    public StudentNoCodelabProgressDto(Long id, String email, String displayName) {
        this.id = id;
        this.email = email;
        this.displayName = displayName;
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
}
