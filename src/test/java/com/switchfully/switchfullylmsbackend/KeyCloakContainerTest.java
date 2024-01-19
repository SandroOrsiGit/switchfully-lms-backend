package com.switchfully.switchfullylmsbackend;

import dasniko.testcontainers.keycloak.KeycloakContainer;
import org.junit.jupiter.api.Test;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Testcontainers(disabledWithoutDocker = true)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class KeyCloakContainerTest {

    @LocalServerPort
    private int port;

    @Container
    static KeycloakContainer keycloak = new KeycloakContainer().withRealmImportFile("keycloak/realm-export.json");

    protected String getBearerToken() {
        System.out.println(keycloak.getAdminPassword());
        System.out.println(keycloak.getAdminUsername());
        try (Keycloak keycloakAdminClient = KeycloakBuilder.builder()
                .serverUrl(keycloak.getAuthServerUrl())
                .realm("master")
                .clientId("admin-cli")
                .username(keycloak.getAdminUsername())
                .password(keycloak.getAdminPassword())
                .build()) {



            String access_token = keycloakAdminClient.tokenManager().getAccessToken().getToken();

            return "Bearer " + access_token;
        } catch (Exception e) {
            System.out.println("Can't obtain an access token from Keycloak! " + e);
        }
        return null;
    }


    @Test
    void givenAuthenticatedUser_whenGetMe_shouldReturnMyInfo() {

        given().header("Authorization", getBearerToken())
                .when()
                .get("/users/me")
                .then()
                .body("username", equalTo("test-user"))
        /*.body("lastName", equalTo(""))
        .body("firstName", equalTo("TestUser"))
        .body("email", equalTo("test-user@howtodoinjava.com"))*/;
    }

}
