package com.switchfully.switchfullylmsbackend.services;

import com.switchfully.switchfullylmsbackend.dtos.classgroups.ClassGroupDto;
import com.switchfully.switchfullylmsbackend.dtos.classgroups.CreateClassGroupDto;
import com.switchfully.switchfullylmsbackend.entities.ClassGroup;
import com.switchfully.switchfullylmsbackend.entities.Coach;
import com.switchfully.switchfullylmsbackend.entities.Course;
import com.switchfully.switchfullylmsbackend.exceptions.ClassGroupNotFoundException;
import com.switchfully.switchfullylmsbackend.exceptions.CourseNotFoundException;
import com.switchfully.switchfullylmsbackend.exceptions.InvalidRoleException;
import com.switchfully.switchfullylmsbackend.mappers.ClassGroupMapper;
import com.switchfully.switchfullylmsbackend.repositories.ClassGroupRepository;
import com.switchfully.switchfullylmsbackend.repositories.CourseRepository;
import com.switchfully.switchfullylmsbackend.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClassGroupService {
    private final ClassGroupRepository classGroupRepository;
    private final ClassGroupMapper classGroupMapper;
    private final CourseRepository courseRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    public ClassGroupService(ClassGroupRepository classGroupRepository, ClassGroupMapper classGroupMapper, CourseRepository courseRepository, UserService userService, UserRepository userRepository) {
        this.classGroupRepository = classGroupRepository;
        this.classGroupMapper = classGroupMapper;
        this.courseRepository = courseRepository;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    public ClassGroupDto createClassGroup(CreateClassGroupDto createClassGroupDto, Coach coach) {
        Course course = courseRepository.findById(createClassGroupDto.getCourseId()).orElseThrow(CourseNotFoundException::new);

        return classGroupMapper.mapClassGroupToClassGroupDto(
                classGroupRepository.save(
                        classGroupMapper.mapCreateClassGroupDtoToClassGroup(createClassGroupDto, course, coach)
                )
        );
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

    public List<ClassGroupDto> getAllClassGroups() {
        return classGroupRepository.findAll().stream().map(classGroupMapper::mapClassGroupToClassGroupDto).collect(Collectors.toList());
    }
}
