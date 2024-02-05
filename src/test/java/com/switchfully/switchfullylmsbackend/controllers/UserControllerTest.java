package com.switchfully.switchfullylmsbackend.controllers;

import com.switchfully.switchfullylmsbackend.dtos.users.UpdateUserDto;
import com.switchfully.switchfullylmsbackend.dtos.users.UserDto;
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
public class UserControllerTest {
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
	void givenCreateStudentDto_whenGetUserByToken_thenReturnUserDto() {
		// given
		String accessToken = getAccessToken("coach@lms.com", "coach");

		// when
		UserDto actual = RestAssured
				.given()
				.auth()
				.oauth2(accessToken)
				.accept(ContentType.JSON)
				.contentType(ContentType.JSON)
				.port(port)
				.when()
				.get("/user")
				.then()
				.assertThat()
				.statusCode(HttpStatus.OK.value())
				.extract()
				.as(UserDto.class);

		//then
		assertThat(actual.getDisplayName()).isEqualTo("Coach");
		assertThat(actual.getEmail()).isEqualTo("coach@lms.com");
		assertThat(actual.getRole()).isEqualTo("coach");
	}
	@Test
	void givenCreateStudentDto_whenGetValidateToken_thenReturnOk() {
		// given
		String accessToken = getAccessToken("coach@lms.com", "coach");

		// when
		RestAssured
				.given()
				.auth()
				.oauth2(accessToken)
				.accept(ContentType.JSON)
				.contentType(ContentType.JSON)
				.port(port)
				.when()
				.get("/user/validate-token")
				.then()
				.assertThat()
				.statusCode(HttpStatus.OK.value());
	}
	@Test
	void givenCreateStudentDto_whenUpdateUser_thenReturnOkAndUserDto() {
		// given
		UpdateUserDto updateUserDto = new UpdateUserDto("test");
		String accessToken = getAccessToken("coach@lms.com", "coach");

		// when
		UserDto actual = RestAssured
				.given()
				.auth()
				.oauth2(accessToken)
				.accept(ContentType.JSON)
				.contentType(ContentType.JSON)
				.port(port)
				.body(updateUserDto)
				.when()
				.put("/user")
				.then()
				.assertThat()
				.statusCode(HttpStatus.OK.value())
				.extract()
				.as(UserDto.class);

		//then
		assertThat(actual.getDisplayName()).isEqualTo(updateUserDto.getDisplayName());
	}


}