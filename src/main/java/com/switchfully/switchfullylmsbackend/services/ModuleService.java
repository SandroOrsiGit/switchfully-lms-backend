package com.switchfully.switchfullylmsbackend.services;

import com.switchfully.switchfullylmsbackend.dtos.modules.CreateModuleDto;
import com.switchfully.switchfullylmsbackend.dtos.modules.ModuleDto;

import com.switchfully.switchfullylmsbackend.entities.*;
import com.switchfully.switchfullylmsbackend.exceptions.CourseNotFoundException;
import com.switchfully.switchfullylmsbackend.exceptions.NotAPartOfThisCourseException;
import com.switchfully.switchfullylmsbackend.mappers.ModuleMapper;
import com.switchfully.switchfullylmsbackend.repositories.ClassGroupRepository;
import com.switchfully.switchfullylmsbackend.repositories.CourseRepository;
import com.switchfully.switchfullylmsbackend.repositories.ModuleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ModuleService {
    private final ModuleRepository moduleRepository;
    private final ModuleMapper moduleMapper;
    private final ClassGroupRepository classGroupRepository;
    private final CourseRepository courseRepository;


    public ModuleService(ModuleRepository moduleRepository, ModuleMapper moduleMapper,
                         ClassGroupRepository classGroupRepository,
                         CourseRepository courseRepository) {
        this.moduleRepository = moduleRepository;
        this.moduleMapper = moduleMapper;
        this.classGroupRepository = classGroupRepository;
        this.courseRepository = courseRepository;
    }

    public ModuleDto createModule(CreateModuleDto createModuleDto) {
        List<Course> courses = createModuleDto.getCourseIds().stream()
                .map(courseId -> courseRepository.findById(courseId).orElseThrow(CourseNotFoundException::new))
                .toList();

        return moduleMapper.mapModuleToModuleDto(
                moduleRepository.save(
                        moduleMapper.mapCreateModuleDtoToModule(createModuleDto, courses)
                )
        );
    }

    public List<ModuleDto> getModulesByCourse(AbstractUser abstractUser, Course course) {
        if (abstractUser instanceof Student student) {
            checkIfStudentIsPartOfCourse(student, course);
        }
        return moduleRepository.findByCourses(course).stream().map(moduleMapper::mapModuleToModuleDto).toList();
    }

    private void checkIfStudentIsPartOfCourse(Student student, Course course) {
        List<ClassGroup> classGroupList = classGroupRepository.findByStudentsId(student.getId());

        List<Course> courseList = classGroupList.stream()
                .map(ClassGroup::getCourse)
                .toList();

        if (!courseList.contains(course)) {
            throw new NotAPartOfThisCourseException();
        }
    }

    public List<ModuleDto> getAllModules() {
        return moduleRepository.findAll().stream()
                .map(moduleMapper::mapModuleToModuleDto)
                .collect(Collectors.toList());
    }
}
