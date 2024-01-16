package com.switchfully.switchfullylmsbackend.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Student extends AbstractUser {
    @OneToMany
    @JoinColumn(name = "student_id")
    private List<CodelabProgress> codelabProgresses;
}
