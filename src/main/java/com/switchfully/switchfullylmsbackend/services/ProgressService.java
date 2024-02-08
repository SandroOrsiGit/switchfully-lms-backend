package com.switchfully.switchfullylmsbackend.services;

import com.switchfully.switchfullylmsbackend.dtos.progresses.ProgressDto;
import com.switchfully.switchfullylmsbackend.entities.Module;
import com.switchfully.switchfullylmsbackend.mappers.ProgressMapper;
import com.switchfully.switchfullylmsbackend.repositories.ProgressRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
@Transactional
public class ProgressService {

    private final ProgressRepository progressRepository;
    private final ProgressMapper progressMapper;

    public ProgressService(ProgressRepository progressRepository, ProgressMapper progressMapper) {
        this.progressRepository = progressRepository;
        this.progressMapper = progressMapper;
    }

    public List<ProgressDto> getProgressOptions() {
        return progressRepository
                .findAll()
                .stream()
                .map(progressMapper::mapProgressToProgressDto)
                .toList();
    }

}
