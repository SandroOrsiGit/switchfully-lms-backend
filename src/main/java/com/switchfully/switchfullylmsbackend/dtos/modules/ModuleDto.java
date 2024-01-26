package com.switchfully.switchfullylmsbackend.dtos.modules;

import com.switchfully.switchfullylmsbackend.entities.Codelab;
import com.switchfully.switchfullylmsbackend.entities.SubModule;

import java.util.List;

public class ModuleDto {
   private Long id;
   private String name;
   private List<Codelab> codelabs;
   private List<SubModule> subModules;

   public ModuleDto() {
   }

   public ModuleDto(Long id, String name, List<Codelab> codelabs, List<SubModule> subModules) {
      this.id = id;
      this.name = name;
      this.codelabs = codelabs;
      this.subModules = subModules;
   }

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
