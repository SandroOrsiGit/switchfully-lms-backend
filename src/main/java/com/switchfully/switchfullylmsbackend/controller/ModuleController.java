package com.switchfully.switchfullylmsbackend.controller;

import com.switchfully.switchfullylmsbackend.service.ModuleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/modules")
public class ModuleController {
   
   private final ModuleService moduleService;
   
   public ModuleController(ModuleService moduleService) {
      this.moduleService = moduleService;
   }
   
   
   
}
