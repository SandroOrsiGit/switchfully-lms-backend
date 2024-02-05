package com.switchfully.switchfullylmsbackend.controllers;

import com.switchfully.switchfullylmsbackend.dtos.modules.CreateModuleDto;
import com.switchfully.switchfullylmsbackend.dtos.modules.ModuleDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ModuleControllerTest {
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
    void givenCreateModuleDtoAndCoach_whenPostingToBackend_thenStatusCodeCreatedIsReturned() {

        //GIVEN
        CreateModuleDto createModuleDto = new CreateModuleDto();
        createModuleDto.setName("TestName");

        createModuleDto.setCourseIds(List.of(1L, 2L));

        String accessToken = getAccessToken("coach@lms.com", "coach");

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

        String accessToken = getAccessToken("student@lms.com", "student");

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

    @Test
    void whenGetAllModules_thenReturnListOfModules() {

        String accessToken = getAccessToken("coach@lms.com", "coach");

        //WHEN
        List<ModuleDto> moduleDtos = RestAssured
                .given()
                .auth()
                .oauth2(accessToken)
                .contentType(ContentType.JSON)
                .port(port)
                .when()
                .get("/modules")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .jsonPath()
                .getList(".", ModuleDto.class);

        //THEN
        assertThat(moduleDtos).hasSize(2);
        assertThat(moduleDtos.get(0).getName()).isEqualTo("Java basics");
        assertThat(moduleDtos.get(1).getName()).isEqualTo(".NET basics");

    }
}
