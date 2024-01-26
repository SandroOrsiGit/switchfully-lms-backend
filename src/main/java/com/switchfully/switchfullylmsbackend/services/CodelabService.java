package com.switchfully.switchfullylmsbackend.services;

import com.switchfully.switchfullylmsbackend.dtos.codelabs.CodelabDto;
import com.switchfully.switchfullylmsbackend.dtos.codelabs.CodelabNoCommentDto;
import com.switchfully.switchfullylmsbackend.dtos.codelabs.CreateCodelabDto;
import com.switchfully.switchfullylmsbackend.dtos.courses.CourseDto;
import com.switchfully.switchfullylmsbackend.entities.Codelab;
import com.switchfully.switchfullylmsbackend.entities.Course;
import com.switchfully.switchfullylmsbackend.entities.Module;
import com.switchfully.switchfullylmsbackend.exceptions.IdNotFoundException;
import com.switchfully.switchfullylmsbackend.mappers.CodelabMapper;
import com.switchfully.switchfullylmsbackend.mappers.CourseMapper;
import com.switchfully.switchfullylmsbackend.repositories.CodelabRepository;
import com.switchfully.switchfullylmsbackend.repositories.CourseRepository;
import com.switchfully.switchfullylmsbackend.repositories.ModuleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CodelabService {
    private final CodelabRepository codelabRepository;
    private final CodelabMapper codelabMapper;
    private final UserService userService;
    private final CourseMapper courseMapper;
    private final CourseRepository courseRepository;
    private final ModuleRepository moduleRepository;

    public CodelabService(CodelabRepository codelabRepository, CodelabMapper codelabMapper, UserService userService, CourseMapper courseMapper,
                          CourseRepository courseRepository, ModuleRepository moduleRepository) {
        this.codelabRepository = codelabRepository;
        this.codelabMapper = codelabMapper;
        this.userService = userService;
        this.courseMapper = courseMapper;
        this.courseRepository = courseRepository;
        this.moduleRepository = moduleRepository;
    }

    public CodelabDto createCodelab(CreateCodelabDto createCodelabDto) {

        Codelab codelab = codelabMapper.mapCreateCodelabDtoToCodelab(createCodelabDto);
        Codelab addedCodelab = codelabRepository.save(codelab);
        return codelabMapper.mapCodelabToCodelabDto(addedCodelab);

    }


    public List<CodelabNoCommentDto> getCodelabs(Long courseId) {
//        Course course = courseMapper.mapCourseDtoToCourse(courseDto);
//        List<Module> moduleList = course.getModules();
        Course course = courseRepository.findById(courseId )
                .orElseThrow( () -> new IdNotFoundException("Course Id not found.") );
        List<Module> moduleList = moduleRepository.findByCourses(course);
                List<Codelab> codelabList = moduleList.stream()
                .map( module -> codelabRepository.findByModuleId(module.getId()) )
                .flatMap(List::stream)
                .toList();
        System.out.println(codelabList);
        return codelabList.stream()
                .map( codelabMapper::mapCodelabToCodelabNoCommentDto)
                .toList();
    }
}
