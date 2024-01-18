package com.switchfully.switchfullylmsbackend.dto;

import com.switchfully.switchfullylmsbackend.entity.Codelab;
import com.switchfully.switchfullylmsbackend.entity.SubModule;

import java.util.List;

public class ModuleDto {
   private final Long id;
   private final String name;
   private final List<Codelab> codelabs;
   private final List<SubModule> subModules;
   
   public ModuleDto(Long id, String name, List<Codelab> codelabs, List<SubModule> subModules) {
      this.id = id;
      this.name = name;
      this.codelabs = codelabs;
      this.subModules = subModules;
   }
   
   //    ---Getters---------------
   public String getName() {
      return name;
   }
   
   public Long getId() {
      return id;
   }

   public List<Codelab> getCodelabs() {
      return codelabs;
   }

   public List<SubModule> getSubModules() {
      return subModules;
   }
}
