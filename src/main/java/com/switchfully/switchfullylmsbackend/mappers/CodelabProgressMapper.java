package com.switchfully.switchfullylmsbackend.mappers;

import com.switchfully.switchfullylmsbackend.dtos.codelabprogresses.CodelabProgressDto;
import com.switchfully.switchfullylmsbackend.entities.CodelabProgress;
import org.springframework.stereotype.Component;

@Component
public class CodelabProgressMapper {

    private CodelabMapper codelabMapper;
    private ProgressMapper progressMapper;

    public CodelabProgressMapper (CodelabMapper codelabMapper, ProgressMapper progressMapper) {
        this.codelabMapper = codelabMapper;
        this.progressMapper = progressMapper;
    }

    public CodelabProgressDto mapCodelabProgressToCodelabProgressDto(CodelabProgress codelabProgress) {
        return new CodelabProgressDto(
                codelabProgress.getId(),
                codelabMapper.mapCodelabToCodelabNoCommentDto(codelabProgress.getCodelab()),
                progressMapper.mapProgressToProgressDto(codelabProgress.getProgress())
        );
    }


}
