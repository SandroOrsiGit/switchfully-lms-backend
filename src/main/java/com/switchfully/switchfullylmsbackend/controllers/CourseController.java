package com.switchfully.switchfullylmsbackend.controllers;

import com.switchfully.switchfullylmsbackend.dtos.courses.*;
import com.switchfully.switchfullylmsbackend.entities.AbstractUser;
import com.switchfully.switchfullylmsbackend.services.*;
import org.springframework.http.*;
import org.springframework.security.access.prepost.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "https://switchfully-lms.netlify.app"})
@RequestMapping(path = "/courses")
public class CourseController {

    private final CourseService courseService;
    private final UserService userService;

    public CourseController(CourseService courseService, UserService userService) {
        this.courseService = courseService;
        this.userService = userService;
    }

    @PostMapping(produces = "application/json")
    @PreAuthorize("hasAuthority('coach')")
    @ResponseStatus(HttpStatus.CREATED)
    public CourseDto createCourse(@RequestBody CreateCourseDto createCourseDto) {
        return courseService.createCourse(createCourseDto);
    }

    @PutMapping(path = "/{courseId}", consumes = "application/json")
    @PreAuthorize("hasAuthority('coach')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCourse(@PathVariable Long courseId, @RequestBody UpdateCourseDto updateCourseDto) {
        courseService.updateCourse(courseId, updateCourseDto);
    }

    @GetMapping(path = "/{courseId}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public CourseDto getCourse(@PathVariable Long courseId) {
        return courseService.getCourseDto(courseId);
    }

    @GetMapping(produces = "application/json")
    @PreAuthorize("hasAnyAuthority('coach', 'student')")
    public List<CourseDto> getCourses(@RequestHeader("Authorization") String bearerToken) {
        AbstractUser abstractUser = userService.getUserByToken(bearerToken);
        return courseService.getCourses(abstractUser);
    }
}
