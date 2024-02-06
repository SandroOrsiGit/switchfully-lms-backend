package com.switchfully.switchfullylmsbackend.services;

import com.switchfully.switchfullylmsbackend.dtos.comments.CommentDto;
import com.switchfully.switchfullylmsbackend.dtos.comments.CreateCommentDto;
import com.switchfully.switchfullylmsbackend.entities.Student;
import com.switchfully.switchfullylmsbackend.repositories.CommentRepository;
import com.switchfully.switchfullylmsbackend.repositories.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
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