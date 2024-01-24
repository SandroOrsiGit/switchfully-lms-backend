package com.switchfully.switchfullylmsbackend.services;

import com.switchfully.switchfullylmsbackend.dtos.classgroups.ClassGroupDto;
import com.switchfully.switchfullylmsbackend.dtos.classgroups.CreateClassGroupDto;
import com.switchfully.switchfullylmsbackend.entities.ClassGroup;
import com.switchfully.switchfullylmsbackend.exceptions.InvalidRoleException;
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
    private final UserService userService;

    public ClassGroupService(ClassGroupRepository classGroupRepository, ClassGroupMapper classGroupMapper, UserService userService) {
        this.classGroupRepository = classGroupRepository;
        this.classGroupMapper = classGroupMapper;
        this.userService = userService;
    }

    public ClassGroupDto addClassGroup(CreateClassGroupDto createClassGroupDto) {
        ClassGroup classGroup = classGroupMapper.mapCreateClassGroupDtoToClassGroup(createClassGroupDto);
        ClassGroup addedClassGroup = classGroupRepository.save(classGroup);
        return classGroupMapper.mapClassGroupToClassGroupDto(addedClassGroup);
    }

    public List<ClassGroupDto> getClassGroupsByUserId(Long userId) {
        String role = userService.getRoleByUserId(userId);
        List<ClassGroup> classGroups;
        if(role.equals("student")){
            classGroups = classGroupRepository.findByStudentsId(userId);
        }else if(role.equals("coach")) {
            classGroups = classGroupRepository.findByCoachesId(userId);
        }else{
            throw new InvalidRoleException("User role is not valid");
        }
        return classGroups
                .stream()
                .map(classGroupMapper::mapClassGroupToClassGroupDto)
                .collect(Collectors.toList());
    }
}
