package com.switchfully.switchfullylmsbackend.mappers;

import com.switchfully.switchfullylmsbackend.dtos.codelabs.CodelabDto;
import com.switchfully.switchfullylmsbackend.dtos.codelabs.CodelabNoCommentDto;
import com.switchfully.switchfullylmsbackend.dtos.codelabs.CreateCodelabDto;
import com.switchfully.switchfullylmsbackend.dtos.comments.CommentDto;
import com.switchfully.switchfullylmsbackend.entities.AbstractModule;
import com.switchfully.switchfullylmsbackend.entities.Codelab;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CodelabMapper {
    private final CommentMapper commentMapper;

    public CodelabMapper(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    public CodelabDto mapCodelabToCodelabDto(Codelab codelab) {
        return new CodelabDto(
                codelab.getId(),
                codelab.getName(),
                mapCommentListToCommentDtoList(codelab)
        );
    }

    public CodelabNoCommentDto mapCodelabToCodelabNoCommentDto(Codelab codelab) {
        return new CodelabNoCommentDto(
                codelab.getId(),
                codelab.getName()
        );
    }

    private List<CommentDto> mapCommentListToCommentDtoList(Codelab codelab) {
        if (codelab.getComments() == null || codelab.getComments().isEmpty()) {
            return new ArrayList<>();
        } else {
            return codelab.getComments().stream()
                    .map(commentMapper::mapCommentToCommentDto)
                    .collect(Collectors.toList());
        }
    }

    public Codelab mapCreateCodelabDtoToCodelab(CreateCodelabDto createCodelabDto, AbstractModule module) {
        return new Codelab(
                createCodelabDto.getName(),
                module
        );
    }
}
