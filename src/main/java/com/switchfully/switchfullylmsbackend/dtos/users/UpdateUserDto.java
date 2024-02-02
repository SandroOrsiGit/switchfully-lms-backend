package com.switchfully.switchfullylmsbackend.dtos.users;

public class UpdateUserDto {

    private Long id;
    private String email;
    private String displayName;
    private String password;

    public UpdateUserDto() {}

    public UpdateUserDto(Long id, String email, String displayName, String password) {
        this.id = id;
        this.email = email;
        this.displayName = displayName;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
