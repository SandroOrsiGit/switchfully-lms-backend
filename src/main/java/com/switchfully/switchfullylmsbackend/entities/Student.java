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
            String displayName,
            String keycloakId
    ) {
        super(email, displayName, keycloakId);

    }

    public Student() {

    }

    public List<CodelabProgress> getCodelabProgresses() {
        return codelabProgresses;
    }

    @Override
    public String getRole() {
        return "student";
    }

    @Override
    public String toString() {
        return "Student{" +
                "codelabProgresses=" + codelabProgresses +
                "} " + super.toString();
    }
}
