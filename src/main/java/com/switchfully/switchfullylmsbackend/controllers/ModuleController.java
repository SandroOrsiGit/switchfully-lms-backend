package com.switchfully.switchfullylmsbackend.controllers;

import com.switchfully.switchfullylmsbackend.dtos.modules.CreateModuleDto;
import com.switchfully.switchfullylmsbackend.dtos.modules.ModuleDto;
import com.switchfully.switchfullylmsbackend.entities.AbstractUser;
import com.switchfully.switchfullylmsbackend.entities.Course;
import com.switchfully.switchfullylmsbackend.services.CourseService;
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
   private final CourseService courseService;

   public ModuleController(ModuleService moduleService, UserService userService, CourseService courseService) {
      this.moduleService = moduleService;
      this.userService = userService;
      this.courseService = courseService;
   }

   @PostMapping(produces = "application/json")
   @PreAuthorize("hasAuthority('coach')")
   @ResponseStatus(HttpStatus.CREATED)
   public ModuleDto createModule(@RequestBody CreateModuleDto createModuleDto) {
      return moduleService.createModule(createModuleDto);
   }


   @GetMapping(path = "/{courseId}")
   @ResponseStatus(HttpStatus.OK)
   public List<ModuleDto> getModulesByCourse(@RequestHeader("Authorization") String bearerToken, @PathVariable Long courseId) {
      AbstractUser abstractUser = userService.getUserByToken(bearerToken);
      Course course = courseService.getCourse(courseId);
      return moduleService.getModulesByCourse(abstractUser, course);

   }
}


