package com.switchfully.switchfullylmsbackend.services;

import com.switchfully.switchfullylmsbackend.dtos.codelabs.CodelabDto;
import com.switchfully.switchfullylmsbackend.dtos.codelabs.CreateCodelabDto;
import com.switchfully.switchfullylmsbackend.dtos.codelabs.UpdateCodelabProgressDto;
import com.switchfully.switchfullylmsbackend.entities.Student;
import com.switchfully.switchfullylmsbackend.exceptions.CodelabNotFoundException;
import com.switchfully.switchfullylmsbackend.exceptions.ModuleNotFoundException;
import com.switchfully.switchfullylmsbackend.exceptions.ProgressNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CodelabServiceTest {
    @Autowired
    private CodelabService codelabService;

    @Test
    void whenCreateCodelab_thenCodelabIsCreatedAndSavedToRepository() {
        //GIVEN
        CreateCodelabDto createCodelabDto = new CreateCodelabDto("createCodelabDto", 1L);

        //WHEN
        CodelabDto resultCodelabDto = codelabService.createCodelab(createCodelabDto);

        //THEN
        assertThat(resultCodelabDto.getName()).isEqualTo(createCodelabDto.getName());
    }

    @Test
    void givenInvalidModuleId_whenGetCodelabsWithWithProgressByModuleId_thenThrowException() {
        //GIVEN
        Student student = new Student();
        Long invalidModuleId = 15000L;

        //WHEN & THEN
        assertThrows(ModuleNotFoundException.class, () -> codelabService.getCodelabsWithProgressByModuleId(invalidModuleId, student));
    }

    @Test
    void givenInvalidCodelabId_whenUpdateCodelabProgress_thenThrowException() {
        //GIVEN
        Student student = new Student();
        Long invalidCodelabId = 15000L;
        Long validProgressId = 1L;
        UpdateCodelabProgressDto updateCodelabProgressDto = new UpdateCodelabProgressDto(invalidCodelabId, validProgressId);

        //WHEN & THEN
        assertThrows(CodelabNotFoundException.class, () -> codelabService.updateCodelabProgress(updateCodelabProgressDto, student));
    }

    @Test
    void givenInvalidProgressId_whenUpdateCodelabProgress_thenThrowException() {
        //GIVEN
        Student student = new Student();
        Long validCodelabId = 1L;
        Long invalidProgressId = 150000L;
        UpdateCodelabProgressDto updateCodelabProgressDto = new UpdateCodelabProgressDto(validCodelabId, invalidProgressId);

        //WHEN & THEN
        assertThrows(ProgressNotFoundException.class, () -> codelabService.updateCodelabProgress(updateCodelabProgressDto, student));
    }

}
