package com.switchfully.switchfullylmsbackend.services;

import com.switchfully.switchfullylmsbackend.dtos.modules.CreateModuleDto;
import com.switchfully.switchfullylmsbackend.dtos.modules.ModuleDto;

import com.switchfully.switchfullylmsbackend.entities.AbstractUser;
import com.switchfully.switchfullylmsbackend.entities.ClassGroup;
import com.switchfully.switchfullylmsbackend.entities.Course;
import com.switchfully.switchfullylmsbackend.entities.Student;
import com.switchfully.switchfullylmsbackend.mappers.ModuleMapper;
import com.switchfully.switchfullylmsbackend.repositories.ClassGroupRepository;
import com.switchfully.switchfullylmsbackend.repositories.CourseRepository;
import com.switchfully.switchfullylmsbackend.repositories.ModuleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.switchfully.switchfullylmsbackend.entities.Module;

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

    public ModuleDto saveModule(CreateModuleDto createModuleDto) {
        return moduleMapper.mapModuleToModuleDto(
                moduleRepository.save(
                        moduleMapper.mapCreateModuleDtoToModule(createModuleDto)
                )
        );
    }

    public List<ModuleDto> getModules(AbstractUser abstractUser) {
        if (abstractUser instanceof Student) {
            List<ClassGroup> classGroupList = classGroupRepository.findByStudentsId(abstractUser.getId() );
            List<Course> courseList = classGroupList.stream()
                    .map(courseRepository::findAllByClassGroup)
                    .flatMap(List::stream)
                    .toList();
            List<Module> moduleList = courseList.stream()
                    .map(moduleRepository::findAllCourse)
                    .flatMap(List::stream)
                    .toList();
            return moduleList.stream()
                    .map(moduleMapper::mapModuleToModuleDto)
                    .toList();
        }
        return null;
    }
}
