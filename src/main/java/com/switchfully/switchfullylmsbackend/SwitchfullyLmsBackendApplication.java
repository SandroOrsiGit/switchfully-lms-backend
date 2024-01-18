package com.switchfully.switchfullylmsbackend;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SwitchfullyLmsBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwitchfullyLmsBackendApplication.class, args);
	}


	@Bean
	public Keycloak keycloak(@Value("${master.keycloak.username}") String adminUsername, @Value("${master.keycloak.password}") String adminPassword) {
		return KeycloakBuilder.builder()
				.serverUrl("https://keycloak.switchfully.com")
				.grantType(OAuth2Constants.PASSWORD)
				.realm("java-2023-10")
				.clientId("admin-cli")
				.username(adminUsername)
				.password(adminPassword)
				.resteasyClient(ResteasyClientBuilder.newBuilder().build())
				.build();
	}

}

