package com.switchfully.switchfullylmsbackend.controllers;

import com.switchfully.switchfullylmsbackend.dtos.classgroups.ClassGroupDto;
import com.switchfully.switchfullylmsbackend.dtos.classgroups.CreateClassGroupDto;
import com.switchfully.switchfullylmsbackend.services.ClassGroupService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path="/class-group")
@CrossOrigin(origins = "http://localhost:4200")
public class ClassGroupController {
    private final ClassGroupService classgroupService;

    public ClassGroupController(ClassGroupService classgroupService) {
        this.classgroupService = classgroupService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "application/json", produces = "application/json")
    @PreAuthorize("hasAuthority('coach')")
    public ClassGroupDto addClassGroup(@RequestBody CreateClassGroupDto createClassgroupDto) {
        return classgroupService.addClassGroup(createClassgroupDto);
    }
    
    @GetMapping(produces = "application/json")
    public List<ClassGroupDto> getClassGroupsByUser(@RequestParam Long userId) {
        return classgroupService.getClassGroupsByUserId(userId);
    }
    
    
}


