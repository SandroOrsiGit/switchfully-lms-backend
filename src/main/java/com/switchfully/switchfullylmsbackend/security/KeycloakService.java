package com.switchfully.switchfullylmsbackend.security;

import com.switchfully.switchfullylmsbackend.dto.user.CreateUserDto;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.ClientResource;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.RealmsResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;


@Service
public class KeycloakService {

    private final RealmResource realmResource;
    private final String clientID;

    public KeycloakService(Keycloak keycloak, @Value("${keycloak.realm}") String realmName, @Value("${keycloak.resource}") String clientId) {
        this.clientID = clientId;
        this.realmResource = keycloak.realm(realmName);
    }

    public void addUser(CreateUserDto createUserDto) {
        String createdUserId = createUser(createUserDto);                                       // create user in keycloak to get Id
        UserResource userResource = realmResource.users().get(createdUserId);                   // get user form keycloak
        userResource.resetPassword(createCredentialRepresentation(createUserDto.getPassword()));// set user password
        addRole( userResource, "student");
    }
    private String createUser(CreateUserDto createUserDto) {
        try {
            return CreatedResponseUtil.getCreatedId( realmResource
                    .users().create( createUserRepresentation(createUserDto.getEmail()) ));
        } catch (WebApplicationException exception) {
            throw new UserAlreadyExistsException(createUserDto.getEmail());
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
