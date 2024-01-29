package com.switchfully.switchfullylmsbackend.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Module extends AbstractModule {
   
   @OneToMany
   @JoinColumn(name = "parent_module_id")
   private List<SubModule> subModules;

   public Module(String name) {
      super(name);
   }

   public Module() {
      super();
   }

   public List<SubModule> getSubModules() {
      return subModules;
   }

   @ManyToMany
   @JoinTable(
           name = "courses_modules",
           joinColumns = @JoinColumn(name = "module_id"),
           inverseJoinColumns = @JoinColumn(name = "course_id")
   )
   private List<Course> courses;

}
