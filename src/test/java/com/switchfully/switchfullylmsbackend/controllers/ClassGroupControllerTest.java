package com.switchfully.switchfullylmsbackend.controllers;

import com.switchfully.switchfullylmsbackend.dtos.classgroups.ClassGroupDto;
import com.switchfully.switchfullylmsbackend.dtos.classgroups.CreateClassGroupDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ClassGroupController classGroupController;
    
    @Value("${keycloak.resource}")
    public String keycloakResource;
    
    @Value("${keycloak.credentials.secret}")
    public String clientSecret;
    
    @Value("${keycloak.realm}")
    public String keycloakTestRealm;
    
    @Test
    void givenCreateClassGroupDtoAndCoach_whenPostingToBackend_thenStatusCodeCreatedIsReturned() {
        //GIVEN
        CreateClassGroupDto createClassGroupDto = new CreateClassGroupDto(
                "TestName",
                LocalDate.now(),
                LocalDate.now().plusDays(1),
                1L);
        
        System.out.println(keycloakTestRealm);
        
        String accessToken = RestAssured
                .given()
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .formParam("client_id", keycloakResource)
                .formParam("client_secret", clientSecret)
                .formParam("grant_type", "password")
                .formParam("username", "coach@lms.com")
                .formParam("password", "coach")
                .when()
                .post("https://keycloak.switchfully.com/realms/" + keycloakTestRealm + "/protocol/openid-connect/token")
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
                LocalDate.now().plusDays(1),
                1L);

        String accessToken = RestAssured
                .given()
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .formParam("client_id", keycloakResource)
                .formParam("client_secret", clientSecret)
                .formParam("grant_type", "password")
                .formParam("username", "student@lms.com")
                .formParam("password", "student")
                .when()
                .post("https://keycloak.switchfully.com/realms/switchfully-lms-test/protocol/openid-connect/token")
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
    
    @Test
    void whenGetClassGroupsByUser_thenReturnListOfClassGroupsAndStatusCodeOK() {
        String accessToken = RestAssured
                .given()
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .formParam("client_id", keycloakResource)
                .formParam("client_secret", clientSecret)
                .formParam("grant_type", "password")
                .formParam("username", "student@lms.com")
                .formParam("password", "student")
                .when()
                .post("https://keycloak.switchfully.com/realms/" + keycloakTestRealm + "/protocol/openid-connect/token")
                .then()
                .extract().body().jsonPath().get("access_token");
        
        ClassGroupDto[] classGroups =
                RestAssured
                        .given()
                        .auth()
                        .oauth2(accessToken)
                        .port(port)
                        .contentType(ContentType.JSON)
                        .param("userId", 1)
                        .when()
                        .get("/class-group")
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.OK.value())
                        .extract()
                        .as(ClassGroupDto[].class);
        
        assertThat(classGroups).hasOnlyElementsOfType(ClassGroupDto.class);
    }

}