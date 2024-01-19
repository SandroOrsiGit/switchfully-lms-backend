package com.switchfully.switchfullylmsbackend.controller;

import com.switchfully.switchfullylmsbackend.dto.ClassGroupDto;
import com.switchfully.switchfullylmsbackend.dto.CreateClassGroupDto;
import com.switchfully.switchfullylmsbackend.service.ClassGroupService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping(path="/classgroup")
public class ClassGroupController {
    private final ClassGroupService classgroupService;

    public ClassGroupController(ClassGroupService classgroupService) {
        this.classgroupService = classgroupService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ClassGroupDto addClassGroup(@RequestBody CreateClassGroupDto createClassgroupDto) {
        return classgroupService.addClassGroup(createClassgroupDto);
    }

    @GetMapping(produces = "application/json")
    public List<ClassGroupDto> getClassGroupsByStudent(@RequestParam Long studentId) {
        System.out.println(classgroupService.getClassGroupsByStudentId(studentId));
        return classgroupService.getClassGroupsByStudentId(studentId);
    }
}
