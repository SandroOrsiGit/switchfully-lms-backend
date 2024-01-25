package com.switchfully.switchfullylmsbackend.dtos.modules;

public class CreateModuleDto {
   
   private String name;

   public CreateModuleDto(String name) {
      this.name = name;
   }

   public CreateModuleDto() {
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }
}
