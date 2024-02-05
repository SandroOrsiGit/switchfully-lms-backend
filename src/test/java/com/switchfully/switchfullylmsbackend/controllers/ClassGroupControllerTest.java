package com.switchfully.switchfullylmsbackend.controllers;

import com.switchfully.switchfullylmsbackend.dtos.classgroups.ClassGroupDto;
import com.switchfully.switchfullylmsbackend.dtos.classgroups.CreateClassGroupDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class ClassGroupControllerTest {

    @LocalServerPort
    private int port;

    @Value ("${spring.security.oauth2.resourceserver.jwt.issuer-uri}/protocol/openid-connect/token")
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
    void givenCreateClassGroupDtoAndCoach_whenPostingToBackend_thenStatusCodeCreatedIsReturned() {
        //GIVEN
        CreateClassGroupDto createClassGroupDto = new CreateClassGroupDto(
                "TestName",
                1L,
                LocalDate.now(),
                LocalDate.now()
        );

        String accessToken = getAccessToken("coach@lms.com", "coach");

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
                .post("/class-groups")
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
                1L,
                LocalDate.now(),
                LocalDate.now().plusDays(1)
        );

        String accessToken = getAccessToken("student@lms.com", "student");

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
                .post("/class-groups")
                .then()
                .assertThat()
                .statusCode(HttpStatus.FORBIDDEN.value());
    }
    
    @Test
    void whenGetClassGroupsByUser_thenReturnListOfClassGroupsAndStatusCodeOK() {
        String accessToken = getAccessToken("coach@lms.com", "coach");
        
        ClassGroupDto[] classGroups =
                RestAssured
                        .given()
                        .auth()
                        .oauth2(accessToken)
                        .port(port)
                        .contentType(ContentType.JSON)
                        .param("userId", 1)
                        .when()
                        .get("/class-groups")
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.OK.value())
                        .extract()
                        .as(ClassGroupDto[].class);
        
        assertThat(classGroups).hasOnlyElementsOfType(ClassGroupDto.class);
    }
}