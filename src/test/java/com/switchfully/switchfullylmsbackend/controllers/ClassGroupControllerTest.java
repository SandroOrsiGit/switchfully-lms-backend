package com.switchfully.switchfullylmsbackend.controllers;

import com.switchfully.switchfullylmsbackend.dtos.classgroups.ClassGroupDto;
import com.switchfully.switchfullylmsbackend.dtos.classgroups.CreateClassGroupDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureTestDatabase
public class ClassGroupControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private ClassGroupController classGroupController;

    // TODO test if this works when we have a test database
    @Test
    void givenCreateClassGroupDtoAndCoach_whenPostingToBackend_thenStatusCodeCreatedIsReturned() {
        //GIVEN
        CreateClassGroupDto createClassGroupDto = new CreateClassGroupDto(
                "TestName",
                LocalDate.now(),
                LocalDate.now().plusDays(1)
        );

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
        ClassGroupDto classGroupDto = RestAssured
                .given()
                .auth()
                .oauth2(accessToken)
                .body(createClassGroupDto)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .port(port)
                .when()
                .post("/class-group")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .as(ClassGroupDto.class);

        assertThat(classGroupDto).isInstanceOf(ClassGroupDto.class);
        assertThat(classGroupDto.getName()).isEqualTo(createClassGroupDto.getName());

    }

    @Test
    void givenCreateClassGroupDtoAndStudent_whenPostingToBackend_thenStatusCodeForbiddenIsReturned() {
        //GIVEN
        CreateClassGroupDto createClassGroupDto = new CreateClassGroupDto(
                "TestName",
                LocalDate.now(),
                LocalDate.now().plusDays(1)
        );

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
                .body(createClassGroupDto)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .port(port)
                .when()
                .post("/class-group")
                .then()
                .assertThat()
                .statusCode(HttpStatus.FORBIDDEN.value());
    }

    // TODO add integration test for getClassGroupsByStudent
}