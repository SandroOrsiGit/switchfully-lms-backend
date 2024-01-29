package com.switchfully.switchfullylmsbackend.services;

import com.switchfully.switchfullylmsbackend.dtos.codelabs.CodelabDto;
import com.switchfully.switchfullylmsbackend.dtos.codelabs.CreateCodelabDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CodelabServiceTest {
    @Autowired
    private CodelabService codelabService;

    @Test
    void whenCreateCodelab_thenCodelabIsCreatedAndSavedToRepository() {
        // given
        CreateCodelabDto createCodelabDto = new CreateCodelabDto("TestingName");

        // when
        CodelabDto resultCodelabDto = codelabService.createCodelab(createCodelabDto);

        // then
        assertThat(resultCodelabDto.getName()).isEqualTo(createCodelabDto.getName());
    }

}
