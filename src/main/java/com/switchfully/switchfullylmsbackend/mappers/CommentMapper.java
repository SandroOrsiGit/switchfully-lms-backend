package com.switchfully.switchfullylmsbackend.mappers;

import com.switchfully.switchfullylmsbackend.dtos.comments.CommentDto;
import com.switchfully.switchfullylmsbackend.dtos.comments.CreateCommentDto;
import com.switchfully.switchfullylmsbackend.entities.Comment;
import com.switchfully.switchfullylmsbackend.entities.Student;
import org.springframework.stereotype.Component;

import java.time.*;

@Component
public class CommentMapper {


    public CommentMapper() {

    }

    public CommentDto mapCommentToCommentDto(Comment comment) {
        return new CommentDto(
                comment.getId(),
                comment.getText(),
                comment.getStudent().getDisplayName(),
                comment.getCreatedDate()
        );
    }

    public Comment mapCreateCommentDtoToComment(CreateCommentDto createCommentDto, Student student) {
        return new Comment(
                createCommentDto.getText(),
                student,
                LocalDate.now()
        );
    }
}
