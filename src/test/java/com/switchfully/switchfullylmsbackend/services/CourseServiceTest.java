package com.switchfully.switchfullylmsbackend.services;

import com.switchfully.switchfullylmsbackend.dtos.courses.CreateCourseDto;
import com.switchfully.switchfullylmsbackend.dtos.courses.CourseDto;
import com.switchfully.switchfullylmsbackend.entities.AbstractUser;
import com.switchfully.switchfullylmsbackend.repositories.UserRepository;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CourseServiceTest {
    @Autowired
    private CourseService courseService;
    @Autowired
    private UserRepository userRepository;
    
    @Test
    void whenCreateCourse_thenCourseIsCreatedAndSavedToRepository() {
        // given
        CreateCourseDto createCourseDto = new CreateCourseDto("testName");

        // when
        CourseDto resultCourseDto = courseService.createCourse(createCourseDto);

        // then
        assertThat(createCourseDto.getName()).isEqualTo(resultCourseDto.getName());
    }

    @Test
    void givenStudent_whenGetCourses_thenReturnCorrectListOfCourseDto() {
        // given
        AbstractUser abstractUser = userRepository.findById(1L).get();
        
        //when
        List<CourseDto> courseDtoList = courseService.getCourses(abstractUser);

        // then
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(courseDtoList).as("size check").hasSize(1);
        softAssertions.assertAll();
    }

    @Test
    void givenCoach_whenGetCourses_thenReturnCorrectListOfCourseDto() {
        // given
        AbstractUser abstractUser = userRepository.findById(9L).get();
        
        // when
        List<CourseDto> courseDtoList = courseService.getCourses(abstractUser);

        // then
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(courseDtoList).as("size check").hasSize(3);
        softAssertions.assertAll();
    }
}
