package com.switchfully.switchfullylmsbackend.controllers;

import com.switchfully.switchfullylmsbackend.dtos.courses.*;
import com.switchfully.switchfullylmsbackend.dtos.modules.*;
import io.restassured.*;
import io.restassured.http.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.jdbc.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.web.server.*;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CourseControllerTest {
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
    void givenCreateCourseDtoAndCoach_whenPostingToBackend_thenStatusCodeCreatedIsReturned() {
        //GIVEN
        CreateCourseDto createCourseDto = new CreateCourseDto("TestName");
        String accessToken = getAccessToken("coach@lms.com", "coach");

        //WHEN
        CourseDto courseDto = RestAssured
                .given()
                .auth()
                .oauth2(accessToken)
                .body(createCourseDto)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .port(port)
                .when()
                .post("/courses")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .as(CourseDto.class);

        assertThat(courseDto).isInstanceOf(CourseDto.class);
        assertThat(courseDto.getName()).isEqualTo(createCourseDto.getName());

    }

    @Test
    void givenCreateCourseDtoAndStudent_whenPostingToBackend_thenStatusCodeForbiddenIsReturned() {
        //GIVEN
        CreateCourseDto createCourseDto = new CreateCourseDto("TestName");
        String accessToken = getAccessToken("student@lms.com", "student");

        //WHEN
        RestAssured
                .given()
                .auth()
                .oauth2(accessToken)
                .body(createCourseDto)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .port(port)
                .when()
                .post("/courses")
                .then()
                .assertThat()
                .statusCode(HttpStatus.FORBIDDEN.value());

    }

    @Test
    void whenCoachGetAllCourses_thenReturnListOfCourses() {

        String accessToken = getAccessToken("coach@lms.com", "coach");

        //WHEN
        List<CourseDto> courseDtos = RestAssured
                .given()
                .auth()
                .oauth2(accessToken)
                .contentType(ContentType.JSON)
                .port(port)
                .when()
                .get("/courses")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .jsonPath()
                .getList(".", CourseDto.class);

        //THEN
        assertThat(courseDtos).hasSize(3);
        assertThat(courseDtos.get(0).getName()).isEqualTo("Java");
        assertThat(courseDtos.get(1).getName()).isEqualTo("Angular");
        assertThat(courseDtos.get(2).getName()).isEqualTo(".NET");
    }

    @Test
    void whenStudentGetAllCourses_thenReturnListOfCourses() {

        String accessToken = getAccessToken("balder@lms.com", "balder");

        //WHEN
        List<CourseDto> courseDtos = RestAssured
                .given()
                .auth()
                .oauth2(accessToken)
                .contentType(ContentType.JSON)
                .port(port)
                .when()
                .get("/courses")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .jsonPath()
                .getList(".", CourseDto.class);

        //THEN
        assertThat(courseDtos).hasSize(1);
        assertThat(courseDtos.get(0).getName()).isEqualTo("Java");
    }

    @Test
    void whenCoachGetCourseById_thenReturnCourse() {
        // GIVEN
        String accessToken = getAccessToken("coach@lms.com", "coach");
        Long courseId = 1L;

        // WHEN
        CourseDto courseDto = RestAssured
                .given()
                .auth()
                .oauth2(accessToken)
                .contentType(ContentType.JSON)
                .port(port)
                .when()
                .get("/courses/{courseId}", courseId)
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(CourseDto.class);

        // THEN
        assertThat(courseDto).isNotNull();
        assertThat(courseDto.getId()).isEqualTo(courseId);
        assertThat(courseDto.getName()).isEqualTo("Java");
    }
}