package com.switchfully.switchfullylmsbackend.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "class_groups")
public class ClassGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;
    @ManyToMany
    @JoinTable(
            name = "class_groups_coaches",
            joinColumns = @JoinColumn(name = "class_group_id"),
            inverseJoinColumns = @JoinColumn(name = "coach_id")
    )
    private List<Coach> coaches = new ArrayList<>();
    @ManyToMany
    @JoinTable(
            name = "class_groups_students",
            joinColumns = @JoinColumn(name = "class_group_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> students =  new ArrayList<>();

    public ClassGroup() {
    }

    public ClassGroup(String name, Course course, LocalDate endDate, LocalDate startDate, List<Coach> coachList) {
        this.name = name;
        this.course = course;
        this.startDate = startDate;
        this.endDate = endDate;
        this.coaches = coachList;
    }

//---Getters------------------

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

    public Course getCourse() {
        return course;
    }

    public List<Coach> getCoaches() {
        return coaches;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    @Override
    public String toString() {
        return "ClassGroup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", course=" + course +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", coaches=" + coaches +
                ", students=" + students +
                '}';
    }
}
