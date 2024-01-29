package com.switchfully.switchfullylmsbackend.controllers;

import com.switchfully.switchfullylmsbackend.dtos.codelabprogresses.CodelabProgressDto;
import com.switchfully.switchfullylmsbackend.dtos.codelabs.CodelabDto;
import com.switchfully.switchfullylmsbackend.dtos.codelabs.CodelabNoCommentDto;
import com.switchfully.switchfullylmsbackend.dtos.codelabs.CreateCodelabDto;
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
    public List<CodelabNoCommentDto> getCodelabs(@RequestParam Long courseId) {
        return codelabService.getCodelabs(courseId);
    }
    @GetMapping(path="/progress", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<CodelabProgressDto> getCodelabsProgress(@RequestParam Long courseId,
                                @RequestHeader("Authorization") String bearerToken) {
        Long studentId = userService.getUserByToken(bearerToken).getId();

        return codelabService.getCodelabsProgress(courseId,studentId);
    }
}
