package com.switchfully.switchfullylmsbackend.services;

import com.switchfully.switchfullylmsbackend.dtos.modules.CreateModuleDto;
import com.switchfully.switchfullylmsbackend.dtos.modules.ModuleDto;

import com.switchfully.switchfullylmsbackend.entities.ClassGroup;
import com.switchfully.switchfullylmsbackend.entities.Course;
import com.switchfully.switchfullylmsbackend.entities.Student;
import com.switchfully.switchfullylmsbackend.entities.Module;
import com.switchfully.switchfullylmsbackend.mappers.ModuleMapper;
import com.switchfully.switchfullylmsbackend.repositories.ClassGroupRepository;
import com.switchfully.switchfullylmsbackend.repositories.CourseRepository;
import com.switchfully.switchfullylmsbackend.repositories.ModuleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

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
        return moduleMapper.mapModuleToModuleDto(
                moduleRepository.save(
                        moduleMapper.mapCreateModuleDtoToModule(createModuleDto)
                )
        );
    }

    public List<ModuleDto> getModulesAsStudent(Student student) {
        List<ClassGroup> classGroupList = classGroupRepository.findByStudentsId(student.getId());
        List<Course> courseList = classGroupList.stream()
                .map(courseRepository::findByClassGroups)
                .flatMap(List::stream)
                .toList();
        List<Module> moduleList = courseList.stream()
                .map(moduleRepository::findByCourses)
                .flatMap(List::stream)
                .toList();
        return moduleList.stream()
                .map(moduleMapper::mapModuleToModuleDto)
                .toList();
    }

    public List<ModuleDto> getModulesByCourse(Course course) {
        return moduleRepository.findByCourses(course).stream().map(moduleMapper::mapModuleToModuleDto).toList();
    }
}
