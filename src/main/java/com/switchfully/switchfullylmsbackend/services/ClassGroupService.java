package com.switchfully.switchfullylmsbackend.services;

import com.switchfully.switchfullylmsbackend.dtos.classgroups.ClassGroupDto;
import com.switchfully.switchfullylmsbackend.dtos.classgroups.CreateClassGroupDto;
import com.switchfully.switchfullylmsbackend.entities.AbstractUser;
import com.switchfully.switchfullylmsbackend.entities.ClassGroup;
import com.switchfully.switchfullylmsbackend.entities.Coach;
import com.switchfully.switchfullylmsbackend.exceptions.ClassGroupNotFoundException;
import com.switchfully.switchfullylmsbackend.exceptions.IdNotFoundException;
import com.switchfully.switchfullylmsbackend.exceptions.InvalidRoleException;
import com.switchfully.switchfullylmsbackend.mappers.ClassGroupMapper;
import com.switchfully.switchfullylmsbackend.repositories.ClassGroupRepository;
import com.switchfully.switchfullylmsbackend.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClassGroupService {
    private final ClassGroupRepository classGroupRepository;
    private final ClassGroupMapper classGroupMapper;
    private final UserService userService;
    private final UserRepository userRepository;

    public ClassGroupService(ClassGroupRepository classGroupRepository, ClassGroupMapper classGroupMapper, UserService userService, UserRepository userRepository) {
        this.classGroupRepository = classGroupRepository;
        this.classGroupMapper = classGroupMapper;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    public ClassGroupDto addClassGroup(CreateClassGroupDto createClassGroupDto) {
        List<Coach> coachList = new ArrayList<>();
        AbstractUser user = userRepository.findById( createClassGroupDto.getCoachId() )
                .orElseThrow( () -> new IdNotFoundException("Coach Id not found."));
        if( user instanceof Coach) coachList.add( (Coach) user );
        else  throw new InvalidRoleException("Passed id is not a coach.");

        ClassGroup classGroup = classGroupMapper.mapCreateClassGroupDtoToClassGroup(createClassGroupDto,coachList);
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
    
    public ClassGroupDto getClassGroupById(Long classGroupId) {
       return classGroupMapper.mapClassGroupToClassGroupDto(classGroupRepository
               .findById(classGroupId).orElseThrow(ClassGroupNotFoundException::new));
    }
}
