package com.switchfully.switchfullylmsbackend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "codelab_progresses")
public class CodelabProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "codelab_id")
    private Codelab codelab;
    @ManyToOne
    @JoinColumn(name = "progress_id")
    private Progress progress;
    @Column(name = "student_id")
    private Long studentId;


    public Long getId() {
        return id;
    }

    public Codelab getCodelab() {
        return codelab;
    }

    public Progress getProgress() {
        return progress;
    }

    public Long getStudentId() {
        return studentId;
    }
}
