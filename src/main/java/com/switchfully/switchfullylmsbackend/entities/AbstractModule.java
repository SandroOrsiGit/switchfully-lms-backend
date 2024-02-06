package com.switchfully.switchfullylmsbackend.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "modules")
public abstract class AbstractModule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "module")
    private final List<Codelab> codelabs = new ArrayList<>();

    public AbstractModule(String name) {
        this.name = name;
    }

    public AbstractModule() {

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Codelab> getCodelabs() {
        return codelabs;
    }

    @Override
    public String toString() {
        return "AbstractModule{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", codelabs=" + codelabs +
                '}';
    }
}
