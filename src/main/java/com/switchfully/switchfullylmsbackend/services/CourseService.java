package com.switchfully.switchfullylmsbackend.services;

import com.switchfully.switchfullylmsbackend.dtos.courses.*;
import com.switchfully.switchfullylmsbackend.entities.*;
import com.switchfully.switchfullylmsbackend.exceptions.CourseNotFoundException;
import com.switchfully.switchfullylmsbackend.mappers.*;
import com.switchfully.switchfullylmsbackend.repositories.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CourseService {

    private final ClassGroupRepository classGroupRepository;
    private final CoachRepository coachRepository;
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final StudentRepository studentRepository;

    public CourseService(ClassGroupRepository classGroupRepository, CoachRepository coachRepository, CourseRepository courseRepository, CourseMapper courseMapper, StudentRepository studentRepository) {
        this.classGroupRepository = classGroupRepository;
        this.coachRepository = coachRepository;
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
        this.studentRepository = studentRepository;
    }

    public CourseDto createCourse(CreateCourseDto createCourseDto) {
        return courseMapper.mapCourseToCourseDto(
                courseRepository.save(
                        courseMapper.mapCreateCourseDtoToCourse(createCourseDto)
                )
        );
    }

    public Course getCourse(Long id) {
        return courseRepository.findById(id).orElseThrow(CourseNotFoundException::new);
    }

    public List<CourseDto> getCourses(AbstractUser abstractUser) {
        Student student = studentRepository.findByEmail(abstractUser.getEmail());
        if (student != null) {
            List<ClassGroup> classGroupList = classGroupRepository.findByStudentsId(student.getId());
            List<Course> courseList = classGroupList.stream()
                    .map(courseRepository::findByClassGroups)
                    .flatMap(List::stream)
                    .toList();

            return courseList.stream().map(courseMapper::mapCourseToCourseDto).toList();
        }

        Coach coach = coachRepository.findByEmail(abstractUser.getEmail());
        if (coach != null) {
            List<Course> courseList = courseRepository.findAll();

            return courseList.stream().map(courseMapper::mapCourseToCourseDto).toList();
        }

        return new ArrayList<>();
    }
}
