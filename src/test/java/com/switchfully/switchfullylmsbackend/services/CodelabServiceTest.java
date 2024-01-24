package com.switchfully.switchfullylmsbackend.services;

import com.switchfully.switchfullylmsbackend.dtos.codelabs.CodelabDto;
import com.switchfully.switchfullylmsbackend.dtos.codelabs.CreateCodelabDto;
import com.switchfully.switchfullylmsbackend.entities.Codelab;
import com.switchfully.switchfullylmsbackend.mappers.CodelabMapper;
import com.switchfully.switchfullylmsbackend.repositories.CodelabRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CodelabServiceTest {
    @Mock
    private CodelabRepository codelabRepository;
    @Mock
    private CodelabMapper codelabMapper;
    @InjectMocks
    private CodelabService codelabService;

    @Test
    void whenCreateCodelab_thenCodelabIsCreatedAndSavedToRepository() {
        // given
        CreateCodelabDto createCodelabDto = new CreateCodelabDto("TestingName");
        Codelab codelabToAdd = new Codelab("TestingName");
        Codelab addedCodelab = new Codelab("TestingName");
        CodelabDto expectedCodelabDto = new CodelabDto(1L, "TestingName", new ArrayList<>());

        // when
        when(codelabMapper.mapCreateCodelabDtoToCodelab(createCodelabDto)).thenReturn(codelabToAdd);
        when(codelabRepository.save(any(Codelab.class))).thenReturn(addedCodelab);
        when(codelabMapper.mapCodelabToCodelabDto(addedCodelab)).thenReturn(expectedCodelabDto);
        CodelabDto resultCodelabDto = codelabService.createCodelab(createCodelabDto);

        // then
        assertEquals(expectedCodelabDto, resultCodelabDto);
    }



}
