package com.switchfully.switchfullylmsbackend.dto;

public class ModuleDto {
   private final Long id;
   private final String name;
   
   public ModuleDto(Long id, String name) {
      this.id = id;
      this.name = name;
   }
   
   //    ---Getters---------------
   public String getName() {
      return name;
   }
   
   public Long getId() {
      return id;
   }
}
