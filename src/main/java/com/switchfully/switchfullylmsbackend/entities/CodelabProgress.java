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
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    public CodelabProgress() {}

    public CodelabProgress(Codelab codelab, Progress progress, Student student) {
        this.codelab = codelab;
        this.progress = progress;
        this.student = student;
    }

    public Long getId() {
        return id;
    }

    public Codelab getCodelab() {
        return codelab;
    }

    public Progress getProgress() {
        return progress;
    }

    public Student getStudent() {
        return student;
    }

    public void setProgress(Progress progress) {
        this.progress = progress;
    }

}
