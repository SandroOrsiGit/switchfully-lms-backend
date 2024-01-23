package com.switchfully.switchfullylmsbackend.mappers;

import com.switchfully.switchfullylmsbackend.dtos.comments.CommentDto;
import com.switchfully.switchfullylmsbackend.entities.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {
    private StudentMapper studentMapper;

    public CommentMapper(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    public CommentDto mapCommentToCommentDto(Comment comment) {
        return new CommentDto(
                comment.getId(),
                comment.getText(),
                studentMapper.mapStudentToStudentDto(comment.getStudent())
        );
    }
}
