package com.switchfully.switchfullylmsbackend.controllers;

import com.switchfully.switchfullylmsbackend.dtos.codelabs.CodelabDto;
import com.switchfully.switchfullylmsbackend.dtos.codelabs.CreateCodelabDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class CodelabControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private CodelabController codelabController;

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
        CreateCodelabDto createCodelabDto = new CreateCodelabDto("Name");

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
                .post("/codelab")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .as(CodelabDto.class);

        assertThat(codelabDto).isInstanceOf(CodelabDto.class);
        assertThat(codelabDto.getName()).isEqualTo(createCodelabDto.getName());

    }


}
