package com.switchfully.switchfullylmsbackend.dtos.modules;

import java.util.List;

public class CreateModuleDto {
   
   private String name;
   private List<Long> courseIds;

   public CreateModuleDto(String name, List<Long> courseIds) {
      this.name = name;
      this.courseIds = courseIds;
   }

   public CreateModuleDto() {
   }

   public String getName() {
      return name;
   }

   public List<Long> getCourseIds() {
      return courseIds;
   }

   public void setCourseIds(List<Long> courseIds) {
      this.courseIds = courseIds;
   }

   public void setName(String name) {
      this.name = name;
   }
}
