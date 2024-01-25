package com.switchfully.switchfullylmsbackend.mappers;

import com.switchfully.switchfullylmsbackend.dtos.progresses.ProgressDto;
import com.switchfully.switchfullylmsbackend.entities.Progress;
import org.springframework.stereotype.Component;

@Component
public class ProgressMapper {

    public ProgressDto mapProgressToProgressDto(Progress progress) {
        return new ProgressDto(
                progress.getId(),
                progress.getName()
        );
    }
}
