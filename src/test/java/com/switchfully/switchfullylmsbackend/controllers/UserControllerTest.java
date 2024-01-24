package com.switchfully.switchfullylmsbackend.controllers;

import com.switchfully.switchfullylmsbackend.dtos.users.CreateUserDto;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import io.restassured.RestAssured;
import static io.restassured.http.ContentType.JSON;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
// The following line disables keycloak registration for this test file
@ActiveProfiles("test")
class UserControllerTest {

    @LocalServerPort
    private int port;

    @Test
    void registerUserTest() {
        // given
        CreateUserDto user = new CreateUserDto("displayName", "user@example.com", "password");

        // when
        RestAssured
                .given()
                .body(user)
                .accept(JSON)
                .contentType(JSON)
                .port(port)
                .when()
                .post("user/register")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value());
    }

}