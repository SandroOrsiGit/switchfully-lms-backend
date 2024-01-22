package com.switchfully.switchfullylmsbackend.services;

import com.switchfully.switchfullylmsbackend.dtos.classgroups.ClassGroupDto;
import com.switchfully.switchfullylmsbackend.dtos.classgroups.CreateClassGroupDto;
import com.switchfully.switchfullylmsbackend.entities.ClassGroup;
import com.switchfully.switchfullylmsbackend.mappers.ClassGroupMapper;
import com.switchfully.switchfullylmsbackend.repositories.ClassGroupRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClassGroupService {
    private final ClassGroupRepository classGroupRepository;
    private final ClassGroupMapper classGroupMapper;

    public ClassGroupService(ClassGroupRepository classGroupRepository, ClassGroupMapper classGroupMapper) {
        this.classGroupRepository = classGroupRepository;
        this.classGroupMapper = classGroupMapper;
    }

    public ClassGroupDto addClassGroup(CreateClassGroupDto createClassGroupDto) {
        ClassGroup classGroup = classGroupMapper.mapCreateClassGroupDtoToClassGroup(createClassGroupDto);
        ClassGroup addedClassGroup = classGroupRepository.save(classGroup);
        return classGroupMapper.mapClassGroupToClassGroupDto(addedClassGroup);
    }

    public List<ClassGroupDto> getClassGroupsByStudentId(Long studentId) {
        return classGroupRepository.findByStudentsId(studentId)
                .stream()
                .map(classGroupMapper::mapClassGroupToClassGroupDto)
                .collect(Collectors.toList());
    }
}
