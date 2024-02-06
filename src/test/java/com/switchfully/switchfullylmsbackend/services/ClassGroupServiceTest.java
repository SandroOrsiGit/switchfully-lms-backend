package com.switchfully.switchfullylmsbackend.services;

import com.switchfully.switchfullylmsbackend.dtos.classgroups.ClassGroupDto;
import com.switchfully.switchfullylmsbackend.dtos.classgroups.CreateClassGroupDto;
import com.switchfully.switchfullylmsbackend.entities.Coach;
import com.switchfully.switchfullylmsbackend.exceptions.ClassGroupNotFoundException;
import com.switchfully.switchfullylmsbackend.exceptions.StudentAlreadyExistsException;
import com.switchfully.switchfullylmsbackend.exceptions.StudentNotFoundException;
import com.switchfully.switchfullylmsbackend.repositories.CoachRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ClassGroupServiceTest {
    @Autowired
    private ClassGroupService classGroupService;
    @Autowired
    private CoachRepository coachRepository;

    @Test
    void whenAddClassGroupAsCoach_thenClassGroupIsCreatedAndAddedToTheDatabase() {
        //GIVEN
        Coach coach = coachRepository.findById(9L).get();
        CreateClassGroupDto createClassGroupDto = new CreateClassGroupDto(
                "TestingService",
                1L,
                LocalDate.now(),
                LocalDate.now().plusDays(1)
        );

        //WHEN
        ClassGroupDto classGroupDto = classGroupService.createClassGroup(createClassGroupDto, coach);

        //THEN
        assertThat(createClassGroupDto.getName()).isEqualTo(classGroupDto.getName());
    }

    @Test
    void givenInvalidClassgroupId_whenGetClassGroupById_thenThrowException() {
        //GIVEN
        Long invalidClassgroupId = 1500000L;

        //WHEN & THEN
        assertThrows(ClassGroupNotFoundException.class, () -> classGroupService.getClassGroupById(invalidClassgroupId));
    }

    @Test
    void givenInvalidStudentId_whenLinkStudentToClassGroup_thenThrowException() {
        //GIVEN
        Long validClassGroupId = 1L;
        Long invalidStudentId = 150000L;

        //WHEN & THEN
        assertThrows(StudentNotFoundException.class, () -> classGroupService.linkStudentToClassGroup(validClassGroupId, invalidStudentId));
    }

    @Test
    void givenStudentAndClassGroup_whenLinkStudentToClassGroupAndLinkIsAlreadyMade_thenThrowException() {
        //GIVEN
        // this link already exists in the test database
        Long classGroupId = 1L;
        Long studentId = 1L;

        //WHEN & THEN
        assertThrows(StudentAlreadyExistsException.class, () -> classGroupService.linkStudentToClassGroup(classGroupId, studentId));
    }
}