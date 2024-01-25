package com.switchfully.switchfullylmsbackend.services;

import com.switchfully.switchfullylmsbackend.dtos.classgroups.ClassGroupDto;
import com.switchfully.switchfullylmsbackend.dtos.classgroups.CreateClassGroupDto;
import com.switchfully.switchfullylmsbackend.entities.ClassGroup;
import com.switchfully.switchfullylmsbackend.exceptions.StudentDoesntExistException;
import com.switchfully.switchfullylmsbackend.mappers.ClassGroupMapper;
import com.switchfully.switchfullylmsbackend.repositories.ClassGroupRepository;
import net.bytebuddy.asm.Advice;
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
                LocalDate.now().plusDays(1));
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
        when(classGroupMapper.mapCreateClassGroupDtoToClassGroup(createClassGroupDto)).thenReturn(classGroupToAdd);
        when(classGroupRepository.save(any(ClassGroup.class))).thenReturn(addedClassGroup);
        when(classGroupMapper.mapClassGroupToClassGroupDto(addedClassGroup)).thenReturn(expectedClassGroupDto);
        ClassGroupDto resultClassGroupDto = classGroupService.addClassGroup(createClassGroupDto);

        // then
        assertEquals(expectedClassGroupDto, resultClassGroupDto);

    }

    @Test
    void givenExistingStudentId_whenGetClassGroupsByStudentId_thenReturnClassGroupDtoList() {
        // given
        Long userId = 1L;
        ClassGroup classGroup1 = new ClassGroup("name1", LocalDate.now(), LocalDate.now().plusDays(1));
        ClassGroup classGroup2 = new ClassGroup("name2", LocalDate.now(), LocalDate.now().plusDays(1));
        ClassGroupDto classGroupDto1 = new ClassGroupDto(1L,"name1", LocalDate.now(), LocalDate.now().plusDays(1), null, new ArrayList<>(), new ArrayList<>());
        ClassGroupDto classGroupDto2 = new ClassGroupDto(2L,"name1", LocalDate.now(), LocalDate.now().plusDays(1), null, new ArrayList<>(), new ArrayList<>());

        List<ClassGroup> classGroupList = Arrays.asList(classGroup1, classGroup2);

        // when
        when(classGroupRepository.findByStudentsId(userId)).thenReturn(classGroupList);
        when(classGroupMapper.mapClassGroupToClassGroupDto(classGroup1)).thenReturn(classGroupDto1);
        when(classGroupMapper.mapClassGroupToClassGroupDto(classGroup2)).thenReturn(classGroupDto2);

        List<ClassGroupDto> result = classGroupService.getClassGroupsByUserId(userId);

        // then
        assertEquals(2, result.size());
        assertEquals(classGroupDto1, result.get(0));
        assertEquals(classGroupDto2, result.get(1));


    }

    @Test
    void givenUndefinedStudentId_whenGetClassGroupsByStudentId_thenReturnClassGroupDtoList() {
        // given
        Long userId = 10000L;


        // when & then
        StudentDoesntExistException exception = assertThrows(StudentDoesntExistException.class, () -> {
            classGroupService.getClassGroupsByUserId(userId);
        });

    }
}