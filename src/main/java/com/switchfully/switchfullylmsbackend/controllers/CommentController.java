package com.switchfully.switchfullylmsbackend.controllers;

import com.switchfully.switchfullylmsbackend.dtos.comments.CreateCommentDto;
import com.switchfully.switchfullylmsbackend.dtos.comments.CommentDto;
import com.switchfully.switchfullylmsbackend.entities.Student;
import com.switchfully.switchfullylmsbackend.services.CommentService;
import com.switchfully.switchfullylmsbackend.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/comments")
@CrossOrigin(origins = {"http://localhost:4200", "https://switchfully-lms.netlify.app"})
public class CommentController {
	private final CommentService commentService;
	private final UserService userService;

	public CommentController(CommentService commentService, UserService userService) {
		this.commentService = commentService;
		this.userService = userService;
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(consumes = "application/json", produces = "application/json")
	@PreAuthorize("hasAuthority('student')")
	public CommentDto createComment(@RequestHeader("Authorization") String bearerToken,  @RequestBody CreateCommentDto createCommentDto) {
		Student student = userService.getStudentByToken(bearerToken);
		return commentService.createComment(createCommentDto, student);
	}
}
