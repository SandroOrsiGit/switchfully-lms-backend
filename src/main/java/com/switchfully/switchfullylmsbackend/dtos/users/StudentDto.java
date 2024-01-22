package com.switchfully.switchfullylmsbackend.dtos.users;

import com.switchfully.switchfullylmsbackend.dtos.codelabprogresses.CodelabProgressDto;

import java.util.List;

public class StudentDto {
    private Long id;
    private String email;
    private String displayName;
    private List<CodelabProgressDto> codelabProgressDtoList;

    public StudentDto() {
    }
    public StudentDto(Long id, String email, String displayName, List<CodelabProgressDto> codelabProgressDtoList) {
        this.id = id;
        this.email = email;
        this.displayName = displayName;
        this.codelabProgressDtoList = codelabProgressDtoList;
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
    public List<CodelabProgressDto> getCodelabProgressDtoList() {
        return codelabProgressDtoList;
    }
}
