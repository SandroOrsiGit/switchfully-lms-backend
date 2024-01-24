package com.switchfully.switchfullylmsbackend.mappers;

import com.switchfully.switchfullylmsbackend.dtos.users.CoachDto;
import com.switchfully.switchfullylmsbackend.entities.Coach;
import org.springframework.stereotype.Component;

@Component
public class CoachMapper {
    public CoachDto mapCoachToCoachDto(Coach coach) {
        return new CoachDto(
                coach.getId(),
                coach.getEmail(),
                coach.getDisplayName()
        );
    }


}
