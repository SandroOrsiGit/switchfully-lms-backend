package com.switchfully.switchfullylmsbackend.services;

import com.switchfully.switchfullylmsbackend.dtos.classgroups.ClassGroupDto;
import com.switchfully.switchfullylmsbackend.dtos.classgroups.CreateClassGroupDto;
import com.switchfully.switchfullylmsbackend.entities.ClassGroup;
import com.switchfully.switchfullylmsbackend.exceptions.StudentDoesntExistException;
import com.switchfully.switchfullylmsbackend.mappers.ClassGroupMapper;
import com.switchfully.switchfullylmsbackend.repositories.ClassGroupRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class ClassGroupServiceTest {

    @Mock
    private ClassGroupRepository classGroupRepository;
    @Mock
    private ClassGroupMapper classGroupMapper;

    @InjectMocks
    private ClassGroupService classGroupService;

    @Test
    void whenAddClassGroupAsCoach_thenClassGroupIsCreatedAndAddedToTheDatabase() {
        // given
        CreateClassGroupDto createClassGroupDto = new CreateClassGroupDto("TestingService",
                LocalDate.now(),
                LocalDate.now().plusDays(1), 1L);
        ClassGroup classGroupToAdd = new ClassGroup("TestingService",
                LocalDate.now(),
                LocalDate.now().plusDays(1));

        ClassGroup addedClassGroup = new ClassGroup("TestingService",
                LocalDate.now(),
                LocalDate.now().plusDays(1));
        ClassGroupDto expectedClassGroupDto = new ClassGroupDto(1L, "TestingService",
                LocalDate.now(),
                LocalDate.now().plusDays(1),
                null,
                new ArrayList<>(),
                new ArrayList<>());


        // when
        when(classGroupMapper.mapCreateClassGroupDtoToClassGroup(createClassGroupDto,null)).thenReturn(classGroupToAdd);
        when(classGroupRepository.save(any(ClassGroup.class))).thenReturn(addedClassGroup);
        when(classGroupMapper.mapClassGroupToClassGroupDto(addedClassGroup)).thenReturn(expectedClassGroupDto);
         ClassGroupDto resultClassGroupDto = classGroupService.addClassGroup(createClassGroupDto);

        // then
        assertEquals(expectedClassGroupDto, resultClassGroupDto);

    }
}