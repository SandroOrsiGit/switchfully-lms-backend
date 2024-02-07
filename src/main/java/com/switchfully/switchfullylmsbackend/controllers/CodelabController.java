package com.switchfully.switchfullylmsbackend.controllers;

import com.switchfully.switchfullylmsbackend.dtos.codelabs.*;
import com.switchfully.switchfullylmsbackend.entities.Student;
import com.switchfully.switchfullylmsbackend.services.CodelabService;
import com.switchfully.switchfullylmsbackend.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "https://switchfully-lms.netlify.app"})
@RequestMapping(path= "/codelabs")
public class CodelabController {
    private final CodelabService codelabService;
    private final UserService userService;

    public CodelabController (CodelabService codelabService, UserService userService) {
        this.codelabService = codelabService;
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "application/json", produces = "application/json")
    @PreAuthorize("hasAuthority('coach')")
    public CodelabDto createCodelab(@RequestBody CreateCodelabDto createCodelabDto) {
        return codelabService.createCodelab(createCodelabDto);
    }

    @GetMapping(path = "/student", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('student')")
    public List<CodelabWithProgressDto> getCodelabsWithProgressByModuleId(@RequestHeader("Authorization") String bearerToken, @RequestParam Long moduleId) {
        Student student = userService.getStudentByToken(bearerToken);

        return codelabService.getCodelabsWithProgressByModuleId(moduleId, student);
    }

    @GetMapping(path="/coach", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('coach')")
    public List<CodelabDto> getCodelabsByModuleId(@RequestParam Long moduleId) {
        return codelabService.getCodelabsByModuleId(moduleId);
    }

    @GetMapping(path = "/{codelabId}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('coach', 'student')")
    public CodelabDto getCodelab(@PathVariable Long codelabId) {
        return codelabService.getCodelab(codelabId);
    }

    @PostMapping(path ="/progress", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('student')")
    public void updateCodelabProgress(@RequestHeader("Authorization") String bearerToken, @RequestBody UpdateCodelabProgressDto updateCodelabProgressDto) {
        Student student = userService.getStudentByToken(bearerToken);

        codelabService.updateCodelabProgress(updateCodelabProgressDto, student);
    }

    @PutMapping(path = "/{codelabId}", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('coach')")
    public void updateCodelab(@PathVariable Long codelabId, @RequestBody UpdateCodelabDto updateCodelabDto) {
        codelabService.updateCodelab(codelabId, updateCodelabDto);
    }

}
