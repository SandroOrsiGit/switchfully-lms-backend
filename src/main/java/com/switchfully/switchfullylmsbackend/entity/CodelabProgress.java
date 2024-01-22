package com.switchfully.switchfullylmsbackend.entity;

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
}
