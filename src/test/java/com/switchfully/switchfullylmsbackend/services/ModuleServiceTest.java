package com.switchfully.switchfullylmsbackend.services;

import com.switchfully.switchfullylmsbackend.dtos.modules.CreateModuleDto;
import com.switchfully.switchfullylmsbackend.dtos.modules.ModuleDto;
import com.switchfully.switchfullylmsbackend.entities.AbstractUser;
import com.switchfully.switchfullylmsbackend.entities.Course;
import com.switchfully.switchfullylmsbackend.entities.Student;

import com.switchfully.switchfullylmsbackend.repositories.CourseRepository;
import com.switchfully.switchfullylmsbackend.repositories.UserRepository;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class ModuleServiceTest {

    @Autowired
    private ModuleService moduleService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CourseRepository courseRepository;

    @Test
    void whenCreateModule_thenModuleIsCreatedAndSavedToRepository() {
        // given
        CreateModuleDto createModuleDto = new CreateModuleDto("testName");

        // when
        ModuleDto resultModuleDto = moduleService.createModule(createModuleDto);

        // then
        assertThat(createModuleDto.getName()).isEqualTo(resultModuleDto.getName());
    }

    @Test
    void givenStudent_whenGetModulesForValidCourse_thenReturnCorrectListOfModuleDto() {
        // given
        AbstractUser abstractUser = userRepository.findById(1L).get();
        Student student = (Student) abstractUser;

        // when
        List<ModuleDto> moduleDtoList = moduleService.getModulesAsStudent(student);

        // then
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(abstractUser.getDisplayName()).as("exists check").isEqualTo("Balder");
        softAssertions.assertThat(moduleDtoList).as("size check").hasSize(1);
        softAssertions.assertThat(moduleDtoList.get(0).getName()).as("module name check").isEqualTo("Java basics");

        softAssertions.assertAll();

    }

    @Test
    void givenCourse_whenGetModules_thenReturnListOfAllModules() {
        // given
        Course course = courseRepository.findById(1L).get();

        // when
        List<ModuleDto> moduleDtoList = moduleService.getModulesByCourse(course);

        // then
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(moduleDtoList).hasSize(1);
        softAssertions.assertThat(moduleDtoList.get(0).getName()).isEqualTo("Java basics");

        softAssertions.assertAll();
    }
}
