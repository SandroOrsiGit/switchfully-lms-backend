package com.switchfully.switchfullylmsbackend.services;

import com.switchfully.switchfullylmsbackend.dtos.courses.*;
import com.switchfully.switchfullylmsbackend.entities.Course;
import com.switchfully.switchfullylmsbackend.exceptions.CourseNotFoundException;
import com.switchfully.switchfullylmsbackend.mappers.*;
import com.switchfully.switchfullylmsbackend.repositories.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

@Service
@Transactional
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;


    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    public CourseDto saveCourse(CreateCourseDto createCourseDto) {
        return courseMapper.mapCourseToCourseDto(
                courseRepository.save(
                        courseMapper.mapCreateCourseDtoToCourse(createCourseDto)
                )
        );
    }

    public Course getCourse(Long id) {
        return courseRepository.findById(id).orElseThrow(CourseNotFoundException::new);
    }
}
