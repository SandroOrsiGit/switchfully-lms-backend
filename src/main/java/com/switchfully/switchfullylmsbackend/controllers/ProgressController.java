package com.switchfully.switchfullylmsbackend.controllers;

import com.switchfully.switchfullylmsbackend.dtos.progresses.ProgressDto;
import com.switchfully.switchfullylmsbackend.services.ProgressService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "https://backend-lms-fjct.onrender.com"})
@RequestMapping(path="/progresses")
public class ProgressController {

    private final ProgressService progressService;

    public ProgressController(ProgressService progressService) {
        this.progressService = progressService;
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('coach', 'student')")
    public List<ProgressDto> getProgressOptions() {
        return progressService.getProgressOptions();
    }

}
