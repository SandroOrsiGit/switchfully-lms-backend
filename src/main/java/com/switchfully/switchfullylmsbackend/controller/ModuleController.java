package com.switchfully.switchfullylmsbackend.controller;

import com.switchfully.switchfullylmsbackend.dto.CreateModuleDto;
import com.switchfully.switchfullylmsbackend.dto.ModuleDto;
import com.switchfully.switchfullylmsbackend.service.ModuleService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping(path = "/modules")
public class ModuleController {
   
   private final ModuleService moduleService;
   
   public ModuleController(ModuleService moduleService) {
      this.moduleService = moduleService;
   }

   @PostMapping(produces = "application/json")
   @PreAuthorize("hasAuthority('coach')")
   @ResponseStatus(HttpStatus.CREATED)
   public ModuleDto createModule(@RequestBody CreateModuleDto createModuleDto) {
      return moduleService.saveModule(createModuleDto);
   }

   
   
}
