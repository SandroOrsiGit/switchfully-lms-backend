package com.switchfully.switchfullylmsbackend.dtos.users;

import com.switchfully.switchfullylmsbackend.dtos.codelabprogresses.CodelabProgressDto;

import java.util.ArrayList;
import java.util.List;

public class StudentDto {
    private Long id;
    private String email;
    private String displayName;

    public StudentDto() {}

    public StudentDto(Long id, String email, String displayName) {
        this.id = id;
        this.email = email;
        this.displayName = displayName;
    }

    //    ---Getters---------------
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
