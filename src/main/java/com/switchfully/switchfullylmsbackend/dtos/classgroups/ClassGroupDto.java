package com.switchfully.switchfullylmsbackend.dtos.classgroups;

import com.switchfully.switchfullylmsbackend.dtos.courses.CourseDto;
import com.switchfully.switchfullylmsbackend.dtos.users.CoachDto;
import com.switchfully.switchfullylmsbackend.dtos.users.StudentNoCodelabProgressDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class ClassGroupDto {
    private Long id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private CourseDto courseDto;
    private List<CoachDto> coachDtoList;
    private List<StudentNoCodelabProgressDto> studentNoCodelabProgressDtoList;

    public ClassGroupDto() {
    }

    public ClassGroupDto(Long id, String name, LocalDate startDate, LocalDate endDate, CourseDto courseDto, List<CoachDto> coachDtoList, List<StudentNoCodelabProgressDto> studentNoCodelabProgressDtoList) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.courseDto = courseDto;
        this.coachDtoList = coachDtoList;
        this.studentNoCodelabProgressDtoList = studentNoCodelabProgressDtoList;
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

    public CourseDto getCourseDto() {
        return courseDto;
    }
    public List<CoachDto> getCoachDtoList() {
        return coachDtoList;
    }
    public List<StudentNoCodelabProgressDto> getStudentNoCodelabProgressDtoList() {
        return studentNoCodelabProgressDtoList;
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
