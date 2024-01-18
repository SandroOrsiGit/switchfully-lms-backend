package com.switchfully.switchfullylmsbackend.service;

import com.switchfully.switchfullylmsbackend.dto.CreateModuleDto;
import com.switchfully.switchfullylmsbackend.dto.ModuleDto;
import com.switchfully.switchfullylmsbackend.entity.Module;

import com.switchfully.switchfullylmsbackend.mapper.ModuleMapper;
import com.switchfully.switchfullylmsbackend.repository.ModuleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ModuleService {
    private final ModuleRepository moduleRepository;
    private final ModuleMapper moduleMapper;


    public ModuleService(ModuleRepository moduleRepository, ModuleMapper moduleMapper) {
        this.moduleRepository = moduleRepository;
        this.moduleMapper = moduleMapper;
    }

    public ModuleDto saveModule(CreateModuleDto createModuleDto) {
        return moduleMapper.mapModuleToModuleDto(
                moduleRepository.save(
                        moduleMapper.mapCreateModuleDtoToModule(createModuleDto)
                )
        );
    }

}
