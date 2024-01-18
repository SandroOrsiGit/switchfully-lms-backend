package com.switchfully.switchfullylmsbackend.controller;

import com.switchfully.switchfullylmsbackend.dto.CreateModuleDto;
import io.restassured.RestAssured;
import io.restassured.authentication.FormAuthConfig;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class ModuleControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private ModuleController moduleController;


    @Test
    void givenCreateModuleDto_whenPostingToBackend_thenStatusCodeCreatedIsReturned() {
        //GIVEN
        CreateModuleDto createModuleDto = new CreateModuleDto();
        createModuleDto.setName("TestName");

        // Configure Form Authentication
        FormAuthConfig formConfig = new FormAuthConfig("http://keycloak.switchfully.com/realms/java-2023-10/protocol/openid-connect/token",
                "coach@lms.com", "coach");


        String accessToken = RestAssured
                .given()
                .auth()
//                .form("your-client-id", "your-client-secret")
                .form("keycloak-example", "Z8kzdqRzPcfWZENlvPebAo3UCjeiQ0UZ")
                .param("grant_type", "password")
//                .param("username", "coach@lms.com")
//                .param("password", "coach")
//                .post("http://keycloak.switchfully.com/realms/java-2023-10/protocol/openid-connect/token")
                .then()
                .extract()
                .path("access_token");


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
                .statusCode(HttpStatus.CREATED.value());

    }
}
