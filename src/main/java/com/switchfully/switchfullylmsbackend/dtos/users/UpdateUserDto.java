package com.switchfully.switchfullylmsbackend.dtos.users;

public class UpdateUserDto {

    private String displayName;

    public UpdateUserDto() {}

    public UpdateUserDto(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }


}
