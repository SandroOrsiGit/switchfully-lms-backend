package com.switchfully.switchfullylmsbackend.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Module extends AbstractModule {
   
   @OneToMany
   @JoinColumn(name = "parent_module_id")
   private final List<SubModule> subModules = new ArrayList<>();
   @ManyToMany
   @JoinTable(
           name = "courses_modules",
           joinColumns = @JoinColumn(name = "module_id"),
           inverseJoinColumns = @JoinColumn(name = "course_id")
   )
   private List<Course> courses;

   public Module(String name, List<Course> courses) {
      super(name);
      this.courses = courses;
   }

   public Module() {
      super();
   }

   public List<SubModule> getSubModules() {
      return subModules;
   }

   public List<Course> getCourses() {
      return courses;
   }
}
