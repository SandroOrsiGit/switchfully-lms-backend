package com.switchfully.switchfullylmsbackend.services;

import com.switchfully.switchfullylmsbackend.dtos.modules.CreateModuleDto;
import com.switchfully.switchfullylmsbackend.dtos.modules.ModuleDto;
import com.switchfully.switchfullylmsbackend.entities.Module;
import com.switchfully.switchfullylmsbackend.mappers.ModuleMapper;
import com.switchfully.switchfullylmsbackend.repositories.ModuleRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@SpringBootTest
public class ModuleServiceTest {
    @Mock
    private ModuleRepository moduleRepository;
    @Mock
    private ModuleMapper moduleMapper;
    @InjectMocks
    private ModuleService moduleService;

    @Test
    void whenCreateModule_thenModuleIsCreatedAndSavedToRepository() {
        // given
        CreateModuleDto createModuleDto = new CreateModuleDto(
                "testName"
        );
        Module moduleToAdd = new Module(
                "testName"
        );
        Module addedModule = new Module(
                "testName"
        );
        ModuleDto expectedModuleDto = new ModuleDto(
                1L, "testName", new ArrayList<>(), new ArrayList<>()
        );

        // when
        when(moduleMapper.mapCreateModuleDtoToModule(createModuleDto)).thenReturn(moduleToAdd);
        when(moduleRepository.save(any(Module.class))).thenReturn(addedModule);
        when(moduleMapper.mapModuleToModuleDto(addedModule)).thenReturn(expectedModuleDto);
        ModuleDto resultModuleDto = moduleService.createModule(createModuleDto);

        // then
        assertEquals(expectedModuleDto, resultModuleDto);
    }
}
