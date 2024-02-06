package com.switchfully.switchfullylmsbackend.services;

import com.switchfully.switchfullylmsbackend.dtos.progresses.ProgressDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ProgressServiceTest {
    @Autowired
    private ProgressService progressService;

    @Test
    void whenGetProgressOptions_thenReturnListOfProgressDto() {
        List<ProgressDto> progressDtoList = progressService.getProgressOptions();

        assertThat(progressDtoList).hasSize(7);
    }

}
