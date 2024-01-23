package com.switchfully.switchfullylmsbackend.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Student extends AbstractUser {
    @OneToMany
    @JoinColumn(name = "student_id")
    private List<CodelabProgress> codelabProgresses;

    public Student(
                   String email,
                   String displayName
                ) {
        super(email, displayName);


    }

    public Student() {

    }
}
