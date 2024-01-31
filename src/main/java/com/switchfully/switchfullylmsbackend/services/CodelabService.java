package com.switchfully.switchfullylmsbackend.services;

import com.switchfully.switchfullylmsbackend.dtos.codelabprogresses.CodelabProgressDto;
import com.switchfully.switchfullylmsbackend.dtos.codelabs.CodelabDto;
import com.switchfully.switchfullylmsbackend.dtos.codelabs.CodelabNoCommentDto;
import com.switchfully.switchfullylmsbackend.dtos.codelabs.CreateCodelabDto;
import com.switchfully.switchfullylmsbackend.dtos.codelabs.UpdateCodelabProgressDto;
import com.switchfully.switchfullylmsbackend.entities.*;
import com.switchfully.switchfullylmsbackend.entities.Module;
import com.switchfully.switchfullylmsbackend.exceptions.CodelabNotFoundException;
import com.switchfully.switchfullylmsbackend.exceptions.CourseNotFoundException;
import com.switchfully.switchfullylmsbackend.exceptions.ModuleNotFoundException;
import com.switchfully.switchfullylmsbackend.exceptions.ProgressNotFoundException;
import com.switchfully.switchfullylmsbackend.mappers.CodelabMapper;
import com.switchfully.switchfullylmsbackend.mappers.CodelabProgressMapper;
import com.switchfully.switchfullylmsbackend.repositories.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CodelabService {
    private final CodelabRepository codelabRepository;
    private final CodelabProgressRepository codelabProgressRepository;
    private final CourseRepository courseRepository;
    private final ModuleRepository moduleRepository;
    private final ProgressRepository progressRepository;
    private final CodelabMapper codelabMapper;
    private final CodelabProgressMapper codelabProgressMapper;

    public CodelabService(CodelabRepository codelabRepository,
                          CodelabMapper codelabMapper,
                          CodelabProgressRepository codelabProgressRepository,
                          CourseRepository courseRepository,
                          ModuleRepository moduleRepository,
                          ProgressRepository progressRepository,
                          CodelabProgressMapper codelabProgressMapper
    ) {
        this.codelabRepository = codelabRepository;
        this.codelabMapper = codelabMapper;
        this.codelabProgressRepository = codelabProgressRepository;
        this.courseRepository = courseRepository;
        this.moduleRepository = moduleRepository;
        this.progressRepository = progressRepository;
        this.codelabProgressMapper = codelabProgressMapper;
    }

    public CodelabDto createCodelab(CreateCodelabDto createCodelabDto) {

        AbstractModule module = moduleRepository.findById(createCodelabDto.getModuleId()).orElseThrow(ModuleNotFoundException::new);

        return codelabMapper.mapCodelabToCodelabDto(
                codelabRepository.save(
                        codelabMapper.mapCreateCodelabDtoToCodelab(createCodelabDto, module)
                )
        );
    }

    public CodelabDto getCodelab(Long codelabId) {
        return codelabMapper.mapCodelabToCodelabDto(codelabRepository.findById(codelabId).orElseThrow(CodelabNotFoundException::new));
    }

    public List<CodelabNoCommentDto> getCodelabs(Long courseId) {
        List<Codelab> codelabList = getCodelabList(courseId);
        return codelabList.stream()
                .map( codelabMapper::mapCodelabToCodelabNoCommentDto)
                .toList();
    }

    public List<CodelabProgressDto> getCodelabsProgress(Long courseId, Long studentId) {
        // get all codelabs in a course
        List<Codelab> codelabList = getCodelabList(courseId);
        // get all progresses of those codelabs
        List<CodelabProgress> codelabProgressList = codelabList.stream()
                .map(codelab -> codelabProgressRepository.findByCodelabId(codelab.getId()))
                .flatMap(List::stream)
        // filter progresses for specific student
                .filter(codelabProgress -> codelabProgress.getStudentId().equals(studentId))
                .toList();
        return codelabProgressList.stream()
                .map(codelabProgressMapper::mapCodelabProgressToCodelabProgressDto)
                .toList();
    }

    private List<Codelab> getCodelabList(Long courseId) {
        Course course = courseRepository.findById(courseId )
                .orElseThrow(CourseNotFoundException::new);
        List<Module> moduleList = moduleRepository.findByCourses(course);
        return moduleList.stream()
                .map(module -> codelabRepository.findByModuleId(module.getId()))
                .flatMap(List::stream)
                .toList();
    }

    private void updateCodelabProgress(UpdateCodelabProgressDto updateCodelabProgressDto, Student student) {
        Codelab codelab = codelabRepository.findById(updateCodelabProgressDto.getCodelabId()).orElseThrow(CodelabNotFoundException::new);
        Progress progress = progressRepository.findById(updateCodelabProgressDto.getProgressId()).orElseThrow(ProgressNotFoundException::new);

    }

}
