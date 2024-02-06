package com.switchfully.switchfullylmsbackend.controllers;

import com.switchfully.switchfullylmsbackend.dtos.codelabs.CodelabDto;
import com.switchfully.switchfullylmsbackend.dtos.codelabs.CodelabWithProgressDto;
import com.switchfully.switchfullylmsbackend.dtos.codelabs.CreateCodelabDto;
import com.switchfully.switchfullylmsbackend.dtos.codelabs.UpdateCodelabDto;
import com.switchfully.switchfullylmsbackend.dtos.codelabs.UpdateCodelabProgressDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CodelabControllerTest {

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
    void givenCreateCodelabAndCoach_whenPostingToBackend_thenCodelabIsCreatedAndSaved() {
        // given
        CreateCodelabDto createCodelabDto = new CreateCodelabDto("Name", 1L);

        String accessToken = getAccessToken("coach@lms.com", "coach");

        // when
        CodelabDto codelabDto = RestAssured
                .given()
                .auth()
                .oauth2(accessToken)
                .body(createCodelabDto)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .port(port)
                .when()
                .post("/codelabs")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .as(CodelabDto.class);

        assertThat(codelabDto).isInstanceOf(CodelabDto.class);
        assertThat(codelabDto.getName()).isEqualTo(createCodelabDto.getName());

    }

    @Test
    void givenCreateCodelabAndStudent_whenPostingToBackend_thenStatusCodeIsForbidden() {
        // given
        CreateCodelabDto createCodelabDto = new CreateCodelabDto("Name", 1L);

        String accessToken = getAccessToken("student@lms.com", "student");

        // when
        RestAssured
                .given()
                .auth()
                .oauth2(accessToken)
                .body(createCodelabDto)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .port(port)
                .when()
                .post("/codelabs")
                .then()
                .assertThat()
                .statusCode(HttpStatus.FORBIDDEN.value());
    }

    @Test
    void givenStudent_whenGetCodelabsWithProgressByModuleId_thenReturnCodelabs() {
        //given
        String accessToken = getAccessToken("student@lms.com", "student");

        // when
        List<CodelabWithProgressDto> actual = RestAssured
                .given()
                .auth()
                .oauth2(accessToken)
                .contentType(ContentType.JSON)
                .port(port)
                .queryParam("moduleId", 1L)
                .when()
                .get("/codelabs/student")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .body()
                .jsonPath()
                .getList(".", CodelabWithProgressDto.class);

        assertThat(actual).hasSize(2);
    }
    @Test
    void givenCoach_whenGetCodelabsByModuleId_thenReturnCodelabs() {
        //given
        String accessToken = getAccessToken("coach@lms.com", "coach");

        // when
        List<CodelabDto> actual = RestAssured
                .given()
                .auth()
                .oauth2(accessToken)
                .contentType(ContentType.JSON)
                .port(port)
                .queryParam("moduleId", 1L)
                .when()
                .get("/codelabs/coach")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .body()
                .jsonPath()
                .getList(".", CodelabDto.class);

        assertThat(actual).hasSize(2);
    }
    @Test
    void givenCoachOrStudent_whenGetCodelab_thenReturnCodelab() {
        //given
        String accessToken = getAccessToken("coach@lms.com", "coach");

        // when
        CodelabDto actual = RestAssured
                .given()
                .auth()
                .oauth2(accessToken)
                .contentType(ContentType.JSON)
                .port(port)
                .when()
                .get("/codelabs/1")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(CodelabDto.class);

        assertThat(actual.getId()).isEqualTo(1L);
    }
    @Test
    void givenStudent_whenUpdateCodelabProgress_thenReturn202() {
        //given
        UpdateCodelabProgressDto updateCodelabProgressDto = new UpdateCodelabProgressDto(1L, 1L);
        String accessToken = getAccessToken("student@lms.com", "student");

        // when
        RestAssured
                .given()
                .auth()
                .oauth2(accessToken)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .port(port)
                .body(updateCodelabProgressDto)
                .when()
                .post("/codelabs/progress")
                .then()
                .assertThat()
                .statusCode(HttpStatus.NO_CONTENT.value());

    }
    @Test
    void givenCoach_whenUpdateCodelab_thenReturn202() {
        //given
        UpdateCodelabDto updateCodelabDto = new UpdateCodelabDto("test", 1L);
        String accessToken = getAccessToken("coach@lms.com", "coach");

        // when
        RestAssured
                .given()
                .auth()
                .oauth2(accessToken)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .port(port)
                .body(updateCodelabDto)
                .when()
                .put("/codelabs/1")
                .then()
                .assertThat()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }
}