package com.switchfully.switchfullylmsbackend.entity;

import jakarta.persistence.*;

@Entity
public class SubModule extends AbstractModule {
    @ManyToOne
    @JoinColumn(name = "parent_module_id")
    private Module parentModule;
}