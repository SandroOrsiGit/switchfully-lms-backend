package com.switchfully.switchfullylmsbackend.entity;

import jakarta.persistence.*;

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
    @ManyToMany
    @JoinTable(
            name = "class_groups_coaches",
            joinColumns = @JoinColumn(name = "class_group_id"),
            inverseJoinColumns = @JoinColumn(name = "coach_id")
    )
    private List<Coach> coaches;
    @ManyToMany
    @JoinTable(
            name = "class_groups_students",
            joinColumns = @JoinColumn(name = "class_group_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> students;

    public ClassGroup() {
    }

    public ClassGroup(String name) {
        this.name = name;
    }


    //---Getters------------------

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
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
}
