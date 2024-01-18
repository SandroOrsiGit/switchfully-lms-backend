package com.switchfully.switchfullylmsbackend.dto.user;

public class CreateUserDto {
    private final String displayName;
    private final String email;

    public CreateUserDto(String displayName, String email) {
        this.displayName = displayName;
        this.email = email;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getEmail() {
        return email;
    }


    @Override
    public String toString() {
        return "CreateUserDto{" +
                "displayName='" + displayName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
