package com.switchfully.switchfullylmsbackend.mapper;

import com.switchfully.switchfullylmsbackend.dto.CreateModuleDto;
import com.switchfully.switchfullylmsbackend.dto.ModuleDto;
import com.switchfully.switchfullylmsbackend.entity.Module;
import org.springframework.stereotype.Component;

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

   public Module mapCreateModuleDtoToModule(CreateModuleDto createModuleDto) {
      return new Module(
              createModuleDto.getName()
      );
   }


}
