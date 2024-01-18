package com.switchfully.switchfullylmsbackend.dto;

public class CreateModuleDto {
   
   private String name;
   
   public CreateModuleDto(String name) {
      this.name = name;
   }
   
   //    ---Getters---------------
   public String getName() {
      return name;
   }
}
