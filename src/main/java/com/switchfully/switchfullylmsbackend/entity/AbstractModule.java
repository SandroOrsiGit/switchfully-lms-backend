package com.switchfully.switchfullylmsbackend.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "modules")
public abstract class AbstractModule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @OneToMany
    @JoinColumn(name = "module_id")
    private List<Codelab> codelabs;
}
