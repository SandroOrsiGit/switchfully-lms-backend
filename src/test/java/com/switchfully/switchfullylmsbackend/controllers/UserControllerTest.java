package com.switchfully.switchfullylmsbackend.controllers;

import com.switchfully.switchfullylmsbackend.dtos.users.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import io.restassured.RestAssured;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class UserControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    UserController userController;

    @Value("${keycloak.resource}")
    public String keycloakResource;

    @Value("${keycloak.credentials.secret}")
    public String clientSecret;

    @Value("${keycloak.realm}")
    public String keycloakTestRealm;

    @Test
    void givenAccessToken_whenGetUserByToken_thenReturnOKAndUser() {

        //GIVEN
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
        UserDto user = RestAssured
                .given()
                .auth()
                .oauth2(accessToken)
                .contentType(JSON)
                .port(port)
                .when()
                .get("/user")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(UserDto.class);

        assertThat(user.getEmail()).isEqualTo("coach@lms.com");
    }
}