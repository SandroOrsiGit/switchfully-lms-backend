package com.switchfully.switchfullylmsbackend.service;

import com.switchfully.switchfullylmsbackend.dto.ClassGroupDto;
import com.switchfully.switchfullylmsbackend.dto.CreateClassGroupDto;
import com.switchfully.switchfullylmsbackend.entity.ClassGroup;
import com.switchfully.switchfullylmsbackend.mapper.ClassGroupMapper;
import com.switchfully.switchfullylmsbackend.repository.ClassGroupRepository;
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
