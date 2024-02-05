package com.switchfully.switchfullylmsbackend.services;

import com.switchfully.switchfullylmsbackend.dtos.comments.CommentDto;
import com.switchfully.switchfullylmsbackend.dtos.comments.CreateCommentDto;
import com.switchfully.switchfullylmsbackend.entities.Comment;
import com.switchfully.switchfullylmsbackend.entities.Student;
import com.switchfully.switchfullylmsbackend.mappers.CommentMapper;
import com.switchfully.switchfullylmsbackend.repositories.CodelabRepository;
import com.switchfully.switchfullylmsbackend.repositories.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
	private final CommentRepository commentRepository;
	private final CommentMapper commentMapper;
	private final CodelabRepository codelabRepository;

	public CommentService(CommentRepository commentRepository, CommentMapper commentMapper, CodelabRepository codelabRepository) {
		this.commentRepository = commentRepository;
		this.commentMapper = commentMapper;
		this.codelabRepository = codelabRepository;
	}

	public CommentDto createComment(CreateCommentDto createCommentDto, Student student) {
		return commentMapper.mapCommentToCommentDto(
				commentRepository.save(
						commentMapper.mapCreateCommentDtoToComment(createCommentDto, student)));
	}
}
