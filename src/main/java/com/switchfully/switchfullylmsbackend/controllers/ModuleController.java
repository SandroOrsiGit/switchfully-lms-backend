package com.switchfully.switchfullylmsbackend.controllers;

import com.switchfully.switchfullylmsbackend.dtos.modules.CreateModuleDto;
import com.switchfully.switchfullylmsbackend.dtos.modules.ModuleDto;
import com.switchfully.switchfullylmsbackend.entities.AbstractUser;
import com.switchfully.switchfullylmsbackend.services.ModuleService;
import com.switchfully.switchfullylmsbackend.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/modules")
public class ModuleController {

   private final ModuleService moduleService;
   private final UserService userService;

   public ModuleController(ModuleService moduleService, UserService userService) {
      this.moduleService = moduleService;
      this.userService = userService;
   }

   @PostMapping(produces = "application/json")
   @PreAuthorize("hasAuthority('coach')")
   @ResponseStatus(HttpStatus.CREATED)
   public ModuleDto createModule(@RequestBody CreateModuleDto createModuleDto) {
      return moduleService.saveModule(createModuleDto);
   }

   @GetMapping()
   @PreAuthorize("hasAuthority('coach')")
   @ResponseStatus(HttpStatus.OK)
   public List<ModuleDto> getModules(@RequestHeader("Authorization") String bearerToken) {
      AbstractUser abstractUser = userService.getUserByToken(bearerToken);
      return moduleService.getModules(abstractUser);
   }



}


