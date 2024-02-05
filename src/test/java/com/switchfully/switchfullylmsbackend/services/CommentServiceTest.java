package com.switchfully.switchfullylmsbackend.services;

import com.switchfully.switchfullylmsbackend.dtos.comments.CommentDto;
import com.switchfully.switchfullylmsbackend.dtos.comments.CreateCommentDto;
import com.switchfully.switchfullylmsbackend.entities.Student;
import com.switchfully.switchfullylmsbackend.repositories.CommentRepository;
import com.switchfully.switchfullylmsbackend.repositories.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CommentServiceTest {
	@Autowired
	private CommentService commentService;
	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private StudentRepository studentRepository;

	@Test
	void givenCreateCommentDto_whenCreateComment_thenReturnCommentDto() {
		//GIVEN
		Student student = studentRepository.findById(1L).get();
		CreateCommentDto createCommentDto = new CreateCommentDto(
				"TestName",
				1L
		);

		//WHEN
		CommentDto actual = commentService.createComment(createCommentDto, student);

		//THEN
		assertThat(actual.getText()).isEqualTo(createCommentDto.getText());
		assertThat(actual.getStudentName()).isEqualTo(student.getDisplayName());
	}
}