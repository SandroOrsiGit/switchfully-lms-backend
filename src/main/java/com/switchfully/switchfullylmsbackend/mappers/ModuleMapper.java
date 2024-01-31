package com.switchfully.switchfullylmsbackend.mappers;

import com.switchfully.switchfullylmsbackend.dtos.modules.AbstractModuleDto;
import com.switchfully.switchfullylmsbackend.dtos.modules.CreateModuleDto;
import com.switchfully.switchfullylmsbackend.dtos.modules.ModuleDto;
import com.switchfully.switchfullylmsbackend.dtos.modules.SubModuleDto;
import com.switchfully.switchfullylmsbackend.entities.AbstractModule;
import com.switchfully.switchfullylmsbackend.entities.Course;
import com.switchfully.switchfullylmsbackend.entities.Module;
import com.switchfully.switchfullylmsbackend.entities.SubModule;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ModuleMapper {

   private final CodelabMapper codelabMapper;
   private final CourseMapper courseMapper;

   public ModuleMapper(CodelabMapper codelabMapper, CourseMapper courseMapper) {
      this.codelabMapper = codelabMapper;
      this.courseMapper = courseMapper;
   }

   public ModuleDto mapModuleToModuleDto(Module module) {
      return new ModuleDto(
              module.getId(),
              module.getName(),
              module.getCodelabs().stream().map(codelabMapper::mapCodelabToCodelabNoCommentDto).collect(Collectors.toList()),
              module.getSubModules().stream().map(this::mapSubModuleToSubModuleDto).collect(Collectors.toList()),
              module.getCourses().stream().map(courseMapper::mapCourseToCourseDto).collect(Collectors.toList()));
   }

   public Module mapCreateModuleDtoToModule(CreateModuleDto createModuleDto, List<Course> courseList) {
      return new Module(
              createModuleDto.getName(),
              courseList
      );
   }

   public SubModuleDto mapSubModuleToSubModuleDto(SubModule subModule) {
      return new SubModuleDto(
              subModule.getId(),
              subModule.getName(),
              subModule.getCodelabs().stream().map(codelabMapper::mapCodelabToCodelabNoCommentDto).collect(Collectors.toList())
      );
   }

   public AbstractModuleDto mapAbstractModuleToAbstractModuleDto(AbstractModule abstractModule) {
      return new AbstractModuleDto(abstractModule.getId(), abstractModule.getName());
   }


}
