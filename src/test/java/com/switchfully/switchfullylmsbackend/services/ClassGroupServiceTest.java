package com.switchfully.switchfullylmsbackend.services;

import com.switchfully.switchfullylmsbackend.dtos.classgroups.ClassGroupDto;
import com.switchfully.switchfullylmsbackend.dtos.classgroups.CreateClassGroupDto;
import com.switchfully.switchfullylmsbackend.entities.Coach;
import com.switchfully.switchfullylmsbackend.repositories.CoachRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

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
        // given
        Coach coach = coachRepository.findById(9L).get();
        CreateClassGroupDto createClassGroupDto = new CreateClassGroupDto(
                "TestingService",
                1L,
                LocalDate.now(),
                LocalDate.now().plusDays(1)
        );

        // when
        ClassGroupDto classGroupDto = classGroupService.createClassGroup(createClassGroupDto, coach);

        // then
        assertThat(createClassGroupDto.getName()).isEqualTo(classGroupDto.getName());
    }
}