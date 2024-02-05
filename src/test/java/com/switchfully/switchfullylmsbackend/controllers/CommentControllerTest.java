package com.switchfully.switchfullylmsbackend.controllers;

import com.switchfully.switchfullylmsbackend.dtos.comments.CommentDto;
import com.switchfully.switchfullylmsbackend.dtos.comments.CreateCommentDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;


import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class CommentControllerTest {

	@LocalServerPort
	private int port;

	@Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}/protocol/openid-connect/token")
	private String url;
	@Value("${keycloak.resource}")
	private String clientId;
	@Value("${keycloak.credentials.secret}")
	private String secret;

	private String getAccessToken(String username, String password) {
		return RestAssured
				.given()
				.contentType("application/x-www-form-urlencoded; charset=utf-8")
				.formParam("client_id", clientId)
				.formParam("client_secret", secret)
				.formParam("grant_type", "password")
				.formParam("username", username)
				.formParam("password", password)
				.when()
				.post(url)
				.then()
				.extract().body().jsonPath().get("access_token");
	}

	@Test
	void givenStudent_whenCreateComment_thenReturnStatusCode201() {
		//GIVEN
		CreateCommentDto createCommentDto = new CreateCommentDto(
				"TestName",
				1L
		);

		String accessToken = getAccessToken("student@lms.com", "student");

		//WHEN
		CommentDto commentDto = RestAssured
				.given()
				.auth()
				.oauth2(accessToken)
				.body(createCommentDto)
				.accept(ContentType.JSON)
				.contentType(ContentType.JSON)
				.port(port)
				.when()
				.post("/comments")
				.then()
				.assertThat()
				.statusCode(HttpStatus.CREATED.value())
				.extract()
				.as(CommentDto.class);

		assertThat(commentDto).isInstanceOf(CommentDto.class);
		assertThat(commentDto.getText()).isEqualTo(createCommentDto.getText());
	}

	@Test
	void givenCoach_whenCreateComment_thenReturnStatusCode403() {
		//GIVEN
		CreateCommentDto createCommentDto = new CreateCommentDto(
				"TestName",
				1L
		);

		String accessToken = getAccessToken("coach@lms.com", "coach");

		//WHEN
		RestAssured
				.given()
				.auth()
				.oauth2(accessToken)
				.body(createCommentDto)
				.accept(ContentType.JSON)
				.contentType(ContentType.JSON)
				.port(port)
				.when()
				.post("/comments")
				.then()
				.assertThat()
				.statusCode(HttpStatus.FORBIDDEN.value());

	}
}