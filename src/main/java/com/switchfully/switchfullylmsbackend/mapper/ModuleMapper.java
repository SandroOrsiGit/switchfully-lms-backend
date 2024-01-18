package com.switchfully.switchfullylmsbackend.mapper;

import com.switchfully.switchfullylmsbackend.dto.ModuleDto;

public class ModuleMapper {
   
   public ModuleDto mapModuleToModuleDto(Module module) {
      return new ModuleDto(
              module.getId
      )
   }


}
