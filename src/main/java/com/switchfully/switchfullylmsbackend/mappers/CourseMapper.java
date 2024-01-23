package com.switchfully.switchfullylmsbackend.mappers;

import com.switchfully.switchfullylmsbackend.dtos.courses.CourseDto;
import com.switchfully.switchfullylmsbackend.entities.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {
    public CourseDto mapCourseToCourseDto(Course course) {
        return new CourseDto(
                course.getId(),
                course.getName()
        );
    }


}