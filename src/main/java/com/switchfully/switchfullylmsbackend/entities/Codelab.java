package com.switchfully.switchfullylmsbackend.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "codelabs")
public class Codelab {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @OneToMany
    @JoinColumn(name = "codelab_id")
    private List<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "module_id")
    private AbstractModule module;


    public Codelab() {

    }

    public Codelab(String name, AbstractModule module) {
        this.name = name;
        this.module = module;
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public AbstractModule getModule() {
        return module;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }


    @Override
    public String toString() {
        return "Codelab{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", comments=" + comments +
                ", module=" + module +
                '}';
    }
}
