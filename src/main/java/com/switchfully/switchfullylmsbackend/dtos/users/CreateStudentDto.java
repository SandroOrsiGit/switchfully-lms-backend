package com.switchfully.switchfullylmsbackend.dtos.users;

public class CreateStudentDto {
    private final String displayName;
    private final String email;

    private final String password;

    public CreateStudentDto(String displayName, String email, String password) {
        this.displayName = displayName;
        this.email = email;
        this.password = password;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "CreateUserDto{" +
                "displayName='" + displayName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
