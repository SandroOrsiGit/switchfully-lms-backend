package com.switchfully.switchfullylmsbackend.controllers;

import com.switchfully.switchfullylmsbackend.dtos.codelabprogresses.CodelabProgressDto;
import com.switchfully.switchfullylmsbackend.dtos.codelabs.*;
import com.switchfully.switchfullylmsbackend.entities.Student;
import com.switchfully.switchfullylmsbackend.services.CodelabService;
import com.switchfully.switchfullylmsbackend.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path="/codelab")
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

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('student')")
    public List<CodelabWithProgressDto> getCodelabsWithProgress(@RequestHeader("Authorization") String bearerToken, @RequestParam Long moduleId) {
        Student student = userService.getStudentByToken(bearerToken);

        return codelabService.getCodelabsWithProgress(moduleId, student);
    }

    @GetMapping(path = "/{codelabId}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('student')")
    public CodelabDto getCodelab(@RequestParam Long codelabId) {
        return codelabService.getCodelab(codelabId);
    }

//    @GetMapping(path="/progress", produces = "application/json")
//    @ResponseStatus(HttpStatus.OK)
//    public List<CodelabProgressDto> getCodelabsProgress(@RequestHeader("Authorization") String bearerToken, @RequestParam Long moduleId) {
//        Student student = userService.getStudentByToken(bearerToken);
//
//        return codelabService.getCodelabsProgressesByModuleId(moduleId, student);
//    }

    @PostMapping(path="/progress", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('student')")
    public void updateCodelabProgress(@RequestHeader("Authorization") String bearerToken, @RequestBody UpdateCodelabProgressDto updateCodelabProgressDto) {
        Student student = userService.getStudentByToken(bearerToken);

        codelabService.updateCodelabProgress(updateCodelabProgressDto, student);
    }

}
