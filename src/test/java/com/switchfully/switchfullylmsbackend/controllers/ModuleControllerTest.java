package com.switchfully.switchfullylmsbackend.controllers;

import com.switchfully.switchfullylmsbackend.dtos.modules.CreateModuleDto;
import com.switchfully.switchfullylmsbackend.dtos.modules.ModuleDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class ModuleControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private ModuleController moduleController;

    @Test
    void givenCreateModuleDtoAndCoach_whenPostingToBackend_thenStatusCodeCreatedIsReturned() {
        //GIVEN
        CreateModuleDto createModuleDto = new CreateModuleDto();
        createModuleDto.setName("TestName");

        String accessToken = RestAssured
                .given()
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .formParam("client_id", "switchfully-lms-test-client")
                .formParam("client_secret", "rcb6nKkHvjxqqWsBIVrelsN6SysGEXD3")
                .formParam("grant_type", "password")
                .formParam("username", "coach@lms.com")
                .formParam("password", "coach")
                .when()
                .post("https://keycloak.switchfully.com/realms/switchfully-lms-test/protocol/openid-connect/token")
                .then()
                .extract().body().jsonPath().get("access_token");

        //WHEN
        ModuleDto moduleDto = RestAssured
                .given()
                .auth()
                .oauth2(accessToken)
                .body(createModuleDto)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .port(port)
                .when()
                .post("/modules")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .as(ModuleDto.class);

        assertThat(moduleDto).isInstanceOf(ModuleDto.class);
        assertThat(moduleDto.getName()).isEqualTo(createModuleDto.getName());

    }

    @Test
    void givenCreateModuleDtoAndStudent_whenPostingToBackend_thenStatusCodeForbiddenIsReturned() {
        //GIVEN
        CreateModuleDto createModuleDto = new CreateModuleDto();
        createModuleDto.setName("TestName");

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
                .body(createModuleDto)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .port(port)
                .when()
                .post("/modules")
                .then()
                .assertThat()
                .statusCode(HttpStatus.FORBIDDEN.value());

    }
}
