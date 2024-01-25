package com.switchfully.switchfullylmsbackend.services;

import com.switchfully.switchfullylmsbackend.dtos.codelabs.CodelabDto;
import com.switchfully.switchfullylmsbackend.dtos.codelabs.CreateCodelabDto;
import com.switchfully.switchfullylmsbackend.entities.Codelab;
import com.switchfully.switchfullylmsbackend.mappers.CodelabMapper;
import com.switchfully.switchfullylmsbackend.repositories.CodelabRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CodelabService {
    private final CodelabRepository codelabRepository;
    private final CodelabMapper codelabMapper;
    private final UserService userService;

    public CodelabService(CodelabRepository codelabRepository, CodelabMapper codelabMapper, UserService userService) {
        this.codelabRepository = codelabRepository;
        this.codelabMapper = codelabMapper;
        this.userService = userService;
    }

    public CodelabDto createCodelab(CreateCodelabDto createCodelabDto) {

        Codelab codelab = codelabMapper.mapCreateCodelabDtoToCodelab(createCodelabDto);
        Codelab addedCodelab = codelabRepository.save(codelab);
        return codelabMapper.mapCodelabToCodelabDto(addedCodelab);

    }


}
