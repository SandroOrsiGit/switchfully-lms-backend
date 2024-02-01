package com.switchfully.switchfullylmsbackend.services;

import com.switchfully.switchfullylmsbackend.dtos.codelabprogresses.CodelabProgressDto;
import com.switchfully.switchfullylmsbackend.dtos.codelabs.*;
import com.switchfully.switchfullylmsbackend.dtos.progresses.ProgressDto;
import com.switchfully.switchfullylmsbackend.entities.*;
import com.switchfully.switchfullylmsbackend.entities.Module;
import com.switchfully.switchfullylmsbackend.exceptions.CodelabNotFoundException;
import com.switchfully.switchfullylmsbackend.exceptions.CourseNotFoundException;
import com.switchfully.switchfullylmsbackend.exceptions.ModuleNotFoundException;
import com.switchfully.switchfullylmsbackend.exceptions.ProgressNotFoundException;
import com.switchfully.switchfullylmsbackend.mappers.CodelabMapper;
import com.switchfully.switchfullylmsbackend.mappers.CodelabProgressMapper;
import com.switchfully.switchfullylmsbackend.mappers.ProgressMapper;
import com.switchfully.switchfullylmsbackend.repositories.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class CodelabService {
    private final CodelabRepository codelabRepository;
    private final CodelabProgressRepository codelabProgressRepository;
    private final CourseRepository courseRepository;
    private final ModuleRepository moduleRepository;
    private final ProgressRepository progressRepository;
    private final ProgressMapper progressMapper;
    private final CodelabMapper codelabMapper;
    private final CodelabProgressMapper codelabProgressMapper;

    public CodelabService(CodelabRepository codelabRepository,
                          CodelabMapper codelabMapper,
                          CodelabProgressRepository codelabProgressRepository,
                          CourseRepository courseRepository,
                          ModuleRepository moduleRepository,
                          ProgressRepository progressRepository,
                          ProgressMapper progressMapper,
                          CodelabProgressMapper codelabProgressMapper
    ) {
        this.codelabRepository = codelabRepository;
        this.codelabMapper = codelabMapper;
        this.codelabProgressRepository = codelabProgressRepository;
        this.courseRepository = courseRepository;
        this.moduleRepository = moduleRepository;
        this.progressRepository = progressRepository;
        this.progressMapper = progressMapper;
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

    public List<CodelabDto> getCodelabsByModuleId(Long moduleId) {
        return codelabRepository.findByModuleId(moduleId).stream().map(codelabMapper::mapCodelabToCodelabDto).toList();
    }

    public List<CodelabWithProgressDto> getCodelabsWithProgress(Long moduleId, Student student) {
        Module module = moduleRepository.findById(moduleId).orElseThrow(ModuleNotFoundException::new);
        List<Codelab> codelabs = codelabRepository.findByModule(module);
        List<CodelabProgress> codelabProgresses = codelabs.stream()
                .map(codelab -> codelabProgressRepository.findByCodelabAndStudent(codelab, student))
                .toList();

        Progress notStartedProgress = progressRepository.findById(7L).orElseThrow(ProgressNotFoundException::new);

        return codelabs.stream().map(codelab -> {
            Progress progress = codelabProgresses.stream()
                    .filter(Objects::nonNull)
                    .filter(codelabProgress -> codelabProgress.getCodelab().equals(codelab))
                    .findFirst().map(CodelabProgress::getProgress)
                    .orElse(notStartedProgress);

            return new CodelabWithProgressDto(codelab.getId(), codelab.getName(), progressMapper.mapProgressToProgressDto(progress));
        }).toList();
    }

    public void updateCodelabProgress(UpdateCodelabProgressDto updateCodelabProgressDto, Student student) {
        Codelab codelab = codelabRepository.findById(updateCodelabProgressDto.getCodelabId()).orElseThrow(CodelabNotFoundException::new);
        Progress progress = progressRepository.findById(updateCodelabProgressDto.getProgressId()).orElseThrow(ProgressNotFoundException::new);
        CodelabProgress codelabProgress = codelabProgressRepository.findByCodelabAndStudent(codelab, student);
        if (codelabProgress == null) {
            codelabProgress = new CodelabProgress(codelab, progress, student);
            codelabProgressRepository.save(codelabProgress);
        } else {
            codelabProgress.setProgress(progress);
            codelabProgressRepository.save(codelabProgress);
        }
    }

    public List<CodelabDto> getCodelabs() {
        return codelabRepository.findAll().stream().map(codelabMapper::mapCodelabToCodelabDto).toList();
    }

//    public List<CodelabProgressDto> getCodelabsProgressesByModuleId(Long moduleId, Student student) {
//        Module module = moduleRepository.findById(moduleId).orElseThrow(ModuleNotFoundException::new);
//        List<Codelab> codelabs = codelabRepository.findByModule(module);
//
//        return codelabs.stream()
//                .map(
//                        codelab -> codelabProgressRepository.findByCodelabAndStudent(codelab, student).stream()
//                                .map(codelabProgressMapper::mapCodelabProgressToCodelabProgressDto)
//                                .toList()
//                )
//                .flatMap(List::stream)
//                .toList();
//    }
}
