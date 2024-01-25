package com.switchfully.switchfullylmsbackend.services;

import com.switchfully.switchfullylmsbackend.dtos.classgroups.ClassGroupDto;
import com.switchfully.switchfullylmsbackend.entities.ClassGroup;
import com.switchfully.switchfullylmsbackend.exceptions.InvalidRoleException;
import com.switchfully.switchfullylmsbackend.mappers.ClassGroupMapper;
import com.switchfully.switchfullylmsbackend.repositories.ClassGroupRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ClassGroupServiceTestObsolete {

    ClassGroupRepository classGroupRepository;
    ClassGroupMapper classGroupMapper;
    UserService userService;
    ClassGroupService classGroupService;
    Long userId;
    ClassGroup classGroup1;
    ClassGroup classGroup2;
    List<ClassGroup> classGroups;
    ClassGroupDto classGroupDto1;
    ClassGroupDto classGroupDto2;

    @BeforeEach
    void init() {
        classGroupRepository = Mockito.mock(ClassGroupRepository.class);
        classGroupMapper = Mockito.mock(ClassGroupMapper.class);
        userService = Mockito.mock(UserService.class);

        classGroupService = new ClassGroupService(classGroupRepository, classGroupMapper, userService);

        userId = 123L;
        classGroup1 = new ClassGroup();
        classGroup2 = new ClassGroup();
        classGroups = List.of(classGroup1, classGroup2);

        classGroupDto1 = new ClassGroupDto();
        classGroupDto2 = new ClassGroupDto();
    }

    @Test
    void givenRoleOfStudent_whenGetClassGroupByUserId_thenReturnListOfClassGroups() {
        //GIVEN
        when(userService.getRoleByUserId(userId)).thenReturn("student");
        when(classGroupRepository.findByStudentsId(userId)).thenReturn(classGroups);
        when(classGroupMapper.mapClassGroupToClassGroupDto(classGroup1)).thenReturn(classGroupDto1);
        when(classGroupMapper.mapClassGroupToClassGroupDto(classGroup2)).thenReturn(classGroupDto2);

        //WHEN
        List<ClassGroupDto> classGroupDtos = classGroupService.getClassGroupsByUserId(userId);

        //THEN
        verify(userService).getRoleByUserId(userId);
        verify(classGroupRepository).findByStudentsId(userId);
        verify(classGroupMapper).mapClassGroupToClassGroupDto(classGroup1);
        verify(classGroupMapper).mapClassGroupToClassGroupDto(classGroup2);
        assertThat(classGroupDtos).containsExactly(classGroupDto1, classGroupDto2);
    }

    @Test
    void givenRoleOfCoach_whenGetClassGroupByUserId_thenReturnListOfClassGroups() {
        //GIVEN
        when(userService.getRoleByUserId(userId)).thenReturn("coach");
        when(classGroupRepository.findByCoachesId(userId)).thenReturn(classGroups);
        when(classGroupMapper.mapClassGroupToClassGroupDto(classGroup1)).thenReturn(classGroupDto1);
        when(classGroupMapper.mapClassGroupToClassGroupDto(classGroup2)).thenReturn(classGroupDto2);

        //WHEN
        List<ClassGroupDto> classGroupDtos = classGroupService.getClassGroupsByUserId(userId);

        //THEN
        verify(userService).getRoleByUserId(userId);
        verify(classGroupRepository).findByCoachesId(userId);
        verify(classGroupMapper).mapClassGroupToClassGroupDto(classGroup1);
        verify(classGroupMapper).mapClassGroupToClassGroupDto(classGroup2);
        assertThat(classGroupDtos).containsExactly(classGroupDto1, classGroupDto2);
    }

    @Test
    void givenOtherRole_whenGetClassGroupByUserId_thenReturnListOfClassGroups() {
        //GIVEN
        when(userService.getRoleByUserId(userId)).thenReturn("class representative");

        //WHEN & THEN
        assertThatThrownBy(() -> classGroupService.getClassGroupsByUserId(userId))
                .isInstanceOf(InvalidRoleException.class)
                .hasMessageContaining("User role is not valid");
    }

}