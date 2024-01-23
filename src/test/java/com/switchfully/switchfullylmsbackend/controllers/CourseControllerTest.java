package com.switchfully.switchfullylmsbackend.controllers;

import com.switchfully.switchfullylmsbackend.dtos.courses.*;
import io.restassured.*;
import io.restassured.http.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.jdbc.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.web.server.*;
import org.springframework.http.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class CourseControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private CourseController courseController;

    @Test
    void givenCreateCourseDtoAndCoach_whenPostingToBackend_thenStatusCodeCreatedIsReturned() {
        //GIVEN
        CreateCourseDto createCourseDto = new CreateCourseDto();
        createCourseDto.setName("TestName");

        String accessToken = RestAssured
                .given()
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .formParam("client_id", "keycloak-example")
                .formParam("client_secret", "Z8kzdqRzPcfWZENlvPebAo3UCjeiQ0UZ")
                .formParam("grant_type", "password")
                .formParam("username", "coach@lms.com")
                .formParam("password", "coach")
                .when()
                .post("https://keycloak.switchfully.com/realms/java-2023-10/protocol/openid-connect/token")
                .then()
                .extract().body().jsonPath().get("access_token");

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
        CreateCourseDto createCourseDto = new CreateCourseDto();
        createCourseDto.setName("TestName");

        String accessToken = RestAssured
                .given()
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .formParam("client_id", "keycloak-example")
                .formParam("client_secret", "Z8kzdqRzPcfWZENlvPebAo3UCjeiQ0UZ")
                .formParam("grant_type", "password")
                .formParam("username", "student@lms.com")
                .formParam("password", "student")
                .when()
                .post("https://keycloak.switchfully.com/realms/java-2023-10/protocol/openid-connect/token")
                .then()
                .extract().body().jsonPath().get("access_token");

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
}