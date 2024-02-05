package com.switchfully.switchfullylmsbackend.dtos.modules;

import com.switchfully.switchfullylmsbackend.dtos.codelabs.CodelabNoCommentDto;
import com.switchfully.switchfullylmsbackend.dtos.courses.CourseDto;

import java.util.List;

public class ModuleDto {
   private final Long id;
   private final String name;
   private final List<CodelabNoCommentDto> codelabs;
   private final List<SubModuleDto> subModules;
   private final List<CourseDto> courses;
   
   public ModuleDto(Long id, String name, List<CodelabNoCommentDto> codelabs, List<SubModuleDto> subModules, List<CourseDto> courses) {
      this.id = id;
      this.name = name;
      this.codelabs = codelabs;
      this.subModules = subModules;
      this.courses = courses;
   }

   public String getName() {
      return name;
   }
   
   public Long getId() {
      return id;
   }

   public List<CodelabNoCommentDto> getCodelabs() {
      return codelabs;
   }

   public List<SubModuleDto> getSubModules() {
      return subModules;
   }

   public List<CourseDto> getCourses() {
      return courses;
   }
}
