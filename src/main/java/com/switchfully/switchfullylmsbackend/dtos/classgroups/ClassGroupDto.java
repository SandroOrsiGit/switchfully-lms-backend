package com.switchfully.switchfullylmsbackend.dtos.classgroups;

import com.switchfully.switchfullylmsbackend.dtos.courses.CourseDto;
import com.switchfully.switchfullylmsbackend.dtos.users.CoachDto;
import com.switchfully.switchfullylmsbackend.dtos.users.StudentDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class ClassGroupDto {
    private Long id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private CourseDto course;
    private List<CoachDto> coaches;
    private List<StudentDto> students;

    public ClassGroupDto() {
    }

    public ClassGroupDto(Long id, String name, LocalDate startDate, LocalDate endDate, CourseDto course, List<CoachDto> coaches, List<StudentDto> students) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.course = course;
        this.coaches = coaches;
        this.students = students;
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public CourseDto getCourse() {
        return course;
    }
    public List<CoachDto> getCoaches() {
        return coaches;
    }
    public List<StudentDto> getStudents() {
        return students;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassGroupDto that = (ClassGroupDto) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
