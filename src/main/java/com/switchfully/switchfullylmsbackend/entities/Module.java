package com.switchfully.switchfullylmsbackend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

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


}
