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
    
    private AbstractModule(Long id, String name, List<Codelab> codelabs) {
    }
    
    public Long getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public List<Codelab> getCodelabs() {
        return codelabs;
    }
    
    
}
