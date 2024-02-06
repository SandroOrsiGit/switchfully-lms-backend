package com.switchfully.switchfullylmsbackend.services;

import com.switchfully.switchfullylmsbackend.dtos.modules.CreateModuleDto;
import com.switchfully.switchfullylmsbackend.dtos.modules.ModuleDto;
import com.switchfully.switchfullylmsbackend.dtos.modules.UpdateModuleDto;
import com.switchfully.switchfullylmsbackend.entities.AbstractUser;
import com.switchfully.switchfullylmsbackend.entities.Course;
import com.switchfully.switchfullylmsbackend.entities.Module;

import com.switchfully.switchfullylmsbackend.exceptions.NotAPartOfThisCourseException;
import com.switchfully.switchfullylmsbackend.repositories.CourseRepository;
import com.switchfully.switchfullylmsbackend.repositories.ModuleRepository;
import com.switchfully.switchfullylmsbackend.repositories.UserRepository;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ModuleServiceTest {

    @Autowired
    private ModuleService moduleService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModuleRepository moduleRepository;
    @Autowired
    private CourseRepository courseRepository;

    @Test
    void whenCreateModule_thenModuleIsCreatedAndSavedToRepository() {
        // given
        CreateModuleDto createModuleDto = new CreateModuleDto("testName", List.of(1L));

        // when
        ModuleDto resultModuleDto = moduleService.createModule(createModuleDto);

        // then
        assertThat(resultModuleDto.getName()).isEqualTo(createModuleDto.getName());
        assertThat(resultModuleDto.getCourses().getFirst().getId()).isEqualTo(createModuleDto.getCourseIds().getFirst());
        assertThat(resultModuleDto.getCourses()).hasSize(1);
    }

    @Test
    void givenStudent_whenGetModulesByCourse_thenReturnCorrectListOfModuleDto() {
        // given
        AbstractUser abstractUser = userRepository.findById(1L).get();
        Course course = courseRepository.findById(1L).get();

        // when
        List<ModuleDto> moduleDtoList = moduleService.getModulesByCourse(abstractUser, course);

        // then
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(abstractUser.getDisplayName()).as("exists check").isEqualTo("Balder");
        softAssertions.assertThat(moduleDtoList).as("size check").hasSize(1);
        softAssertions.assertThat(moduleDtoList.get(0).getName()).as("module name check").isEqualTo("Java basics");

        softAssertions.assertAll();
    }

    @Test
    void givenCoachAndCourse_whenGetModulesByCourse_thenReturnListOfAllModules() {
        // given
        AbstractUser abstractUser = userRepository.findById(9L).get();
        Course course = courseRepository.findById(1L).get();


        // when
        List<ModuleDto> moduleDtoList = moduleService.getModulesByCourse(abstractUser, course);

        // then
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(moduleDtoList).hasSize(1);
        softAssertions.assertThat(moduleDtoList.get(0).getName()).isEqualTo("Java basics");

        softAssertions.assertAll();
    }

    @Test
    void whenGetAllModules_thenReturnListOfAllModules() {
        // when
        List<ModuleDto> actual = moduleService.getAllModules();

        // then
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(actual).hasSize(2);

        softAssertions.assertAll();
    }

    @Test
    void whenGetModuleByCodelab_thenReturnModule() {
        // when
        ModuleDto actual = moduleService.getModuleByCodelab(1L);

        // then
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(actual.getName()).isEqualTo("Java basics");
        softAssertions.assertThat(actual.getId()).isEqualTo(1L);

        softAssertions.assertAll();
    }
    @Test
    void whenGetModuleById_thenReturnModule() {
        // when
        ModuleDto actual = moduleService.getModuleById(1L);

        // then
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(actual.getName()).isEqualTo("Java basics");

        softAssertions.assertAll();
    }
    @Test
    void givenStudentNotPartOfCourse_whenCheckIfStudentIsPartOfCourse_thenThrowNotAPartOfThisCourseException() {
        //given
        AbstractUser abstractUser = userRepository.findById(3L).get();
        Course course = courseRepository.findById(1L).get();

        // when + then
        assertThatThrownBy(() -> moduleService.getModulesByCourse(abstractUser, course)).isInstanceOf(NotAPartOfThisCourseException.class).hasMessageContaining("User is not part of this course");
    }

    @Test
    void givenUpdateModuleDto_whenUpdatingModule_thenModuleIsUpdated() {
        // given
        Module module = moduleRepository.findById(1L).get();
        UpdateModuleDto updateModuleDto = new UpdateModuleDto("NewName", List.of(1L));

        // when
        moduleService.updateModule(module.getId(), updateModuleDto);

        // then
        assertThat(moduleRepository.findById(1L).get().getName()).isEqualTo(updateModuleDto.getName());
    }
}
