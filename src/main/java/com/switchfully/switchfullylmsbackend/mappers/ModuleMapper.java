package com.switchfully.switchfullylmsbackend.mappers;

import com.switchfully.switchfullylmsbackend.dtos.modules.CreateModuleDto;
import com.switchfully.switchfullylmsbackend.dtos.modules.ModuleDto;
import com.switchfully.switchfullylmsbackend.entities.Course;
import com.switchfully.switchfullylmsbackend.entities.Module;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ModuleMapper {
   
   public ModuleDto mapModuleToModuleDto(Module module) {
      return new ModuleDto(
              module.getId(),
              module.getName(),
              module.getCodelabs(),
              module.getSubModules()
      );
   }

   public Module mapCreateModuleDtoToModule(CreateModuleDto createModuleDto, List<Course> courseList) {
      return new Module(
              createModuleDto.getName(),
              courseList
      );
   }


}
