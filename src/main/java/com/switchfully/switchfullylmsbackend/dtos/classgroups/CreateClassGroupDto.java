package com.switchfully.switchfullylmsbackend.dtos.classgroups;

import java.time.LocalDate;

public class CreateClassGroupDto {
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;

    public CreateClassGroupDto() {
    }

    public CreateClassGroupDto(String name, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // --- Getters ---------------------
    public String getName() {
        return name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
