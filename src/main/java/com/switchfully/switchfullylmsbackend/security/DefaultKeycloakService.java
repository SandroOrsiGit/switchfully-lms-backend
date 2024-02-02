package com.switchfully.switchfullylmsbackend.security;

import com.switchfully.switchfullylmsbackend.dtos.users.*;
import com.switchfully.switchfullylmsbackend.entities.*;
import com.switchfully.switchfullylmsbackend.exceptions.UserAlreadyExistsException;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.ws.rs.WebApplicationException;
import java.util.Arrays;


@Service
@Profile("!test")
public class DefaultKeycloakService implements KeycloakService {

    private final RealmResource realmResource;
    private final String clientID;

    public DefaultKeycloakService(Keycloak keycloak, @Value("${keycloak.realm}") String realmName, @Value("${keycloak.resource}") String clientId) {
        this.clientID = clientId;
        this.realmResource = keycloak.realm(realmName);
    }

    public String addUser(CreateStudentDto createStudentDto) {
        String createdUserId = createUser(createStudentDto);
        UserResource userResource = realmResource.users().get(createdUserId);
        userResource.resetPassword(createCredentialRepresentation(createStudentDto.getPassword()));
        addRole( userResource, "student");
        return createdUserId;
    }


    private String createUser(CreateStudentDto createStudentDto) {
        try {
            return CreatedResponseUtil.getCreatedId( realmResource
                    .users().create( createUserRepresentation(createStudentDto.getEmail()) ));
        } catch (WebApplicationException exception) {
            throw new UserAlreadyExistsException(createStudentDto.getEmail());
        }
    }

    private CredentialRepresentation createCredentialRepresentation(String password) {
        CredentialRepresentation passwordCredentials = new CredentialRepresentation();
        passwordCredentials.setTemporary(false);
        passwordCredentials.setType(CredentialRepresentation.PASSWORD);
        passwordCredentials.setValue(password);
        return passwordCredentials;
    }


    private void addRole(UserResource userResource, String userRole) {
        ClientRepresentation clientRepresentation = realmResource
                .clients().findByClientId( clientID ).get(0);
        RoleRepresentation roleRepresentation = realmResource
                .clients().get( clientRepresentation.getId() )
                .roles().get( userRole ).toRepresentation();
        userResource.roles().clientLevel( clientRepresentation.getId() ).add( Arrays.asList(roleRepresentation) );
    }

    private UserRepresentation createUserRepresentation(String username) {
        UserRepresentation user = new UserRepresentation();
        user.setUsername(username);
        user.setEnabled(true);
        return user;
    }

}
