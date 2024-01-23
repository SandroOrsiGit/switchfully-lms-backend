package com.switchfully.switchfullylmsbackend.services;

import com.switchfully.switchfullylmsbackend.dtos.classgroups.ClassGroupDto;
import com.switchfully.switchfullylmsbackend.dtos.classgroups.CreateClassGroupDto;
import com.switchfully.switchfullylmsbackend.entities.ClassGroup;
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

import static org.junit.jupiter.api.Assertions.*;

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
        CreateClassGroupDto createClassGroupDto = new CreateClassGroupDto("TestingService",
                LocalDate.now(),
                LocalDate.now().plusDays(1));
        ClassGroup classGroupToAdd = new ClassGroup("TestingService",
                LocalDate.now(),
                LocalDate.now().plusDays(1));

        ClassGroup addedClassGroup = new ClassGroup("TestingService",
                LocalDate.now(),
                LocalDate.now().plusDays(1));
        ClassGroupDto expectedClassGroupDto = new ClassGroupDto("TestingService",
                LocalDate.now(),
                LocalDate.now().plusDays(1));

        //Mocks
        when(classGroupMapper.map)

    }

    @Test
    void getClassGroupsByStudentId() {
    }
}