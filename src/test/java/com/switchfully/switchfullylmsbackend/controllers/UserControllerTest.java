package com.switchfully.switchfullylmsbackend.controllers;

import com.switchfully.switchfullylmsbackend.dtos.users.CreateUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import io.restassured.RestAssured;
import static io.restassured.http.ContentType.JSON;
import org.junit.jupiter.api.Test;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class UserControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    UserController userController;

    @Test
    void UserControllerTest() {
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
                .post("/register")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value());
    }

}