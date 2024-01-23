package com.switchfully.switchfullylmsbackend.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "modules")
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


}
