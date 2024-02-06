package com.switchfully.switchfullylmsbackend.dtos.classgroups;

import java.time.LocalDate;

public class CreateClassGroupDto {
    private String name;
    private Long courseId;
    private LocalDate startDate;
    private LocalDate endDate;


    public CreateClassGroupDto() {
    }

    public CreateClassGroupDto(String name, Long courseId, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.courseId = courseId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
