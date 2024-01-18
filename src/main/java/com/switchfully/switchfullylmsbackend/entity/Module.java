package com.switchfully.switchfullylmsbackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

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
