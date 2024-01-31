package com.switchfully.switchfullylmsbackend.controllers;

import com.switchfully.switchfullylmsbackend.dtos.classgroups.ClassGroupDto;
import com.switchfully.switchfullylmsbackend.dtos.classgroups.CreateClassGroupDto;
import com.switchfully.switchfullylmsbackend.entities.Coach;
import com.switchfully.switchfullylmsbackend.services.ClassGroupService;
import com.switchfully.switchfullylmsbackend.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path="/class-groups")
@CrossOrigin(origins = "http://localhost:4200")
public class ClassGroupController {
    private final ClassGroupService classgroupService;
    private final UserService userService;

    public ClassGroupController(ClassGroupService classgroupService, UserService userService) {
        this.classgroupService = classgroupService;
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "application/json", produces = "application/json")
    @PreAuthorize("hasAuthority('coach')")
    public ClassGroupDto createClassGroup(@RequestHeader("Authorization") String bearerToken, @RequestBody CreateClassGroupDto createClassgroupDto) {
        System.out.println(createClassgroupDto);
        Coach coach = userService.getCoachByToken(bearerToken);
        return classgroupService.createClassGroup(createClassgroupDto, coach);
    }
    
    @GetMapping(path = "/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('coach', 'student')")
    public ClassGroupDto getClassGroupById( @PathVariable Long id ) {
        return classgroupService.getClassGroupById(id);
    }
    
    @GetMapping(produces = "application/json")
    @PreAuthorize("hasAnyAuthority('coach', 'student')")
    public List<ClassGroupDto> getClassGroupsByUser(@RequestParam Long userId) {
        return classgroupService.getClassGroupsByUserId(userId);
    }
}


