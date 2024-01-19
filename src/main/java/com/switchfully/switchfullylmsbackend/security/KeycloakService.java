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
        String createdUserId = createUser(createUserDto);           // create user in keycloak to get Id
        UserResource userResource =  getUser(createdUserId);
        System.out.println("addUser userResource: " + userResource.toString() );
        userResource.resetPassword(createCredentialRepresentation(createUserDto.getPassword()));
        // TODO addUser creates error
        addRole( userResource, "student");
    }
    private String createUser(CreateUserDto createUserDto) {
        try {
            return CreatedResponseUtil.getCreatedId(createUser(createUserDto.getEmail()));
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
////        user.roles().clientLevel(getClientUUID()).add(Lists.newArrayList(getRole(roleName)));
//           RoleRepresentation returnedRole = getRole(roleName);
//            System.out.println("addRole getRole: "+returnedRole);
//
//         realmResource.users().get( getClientUUID() ).roles().realmLevel().add( List.of( returnedRole ));
//        String userRole = "student";
//        List<RoleRepresentation> roleRepresentationList = userResource.roles().realmLevel().listAvailable();
//        List<RoleRepresentation> roleRepresentationList = realmResource.clients().findByClientId( clientID );
        ClientRepresentation clientRepresentation = realmResource.clients().findByClientId( clientID ).get(0);
        System.out.println("list= "+clientRepresentation);
        RoleRepresentation roleRepresentation = realmResource
                .clients().get( clientRepresentation.getId() )
                .roles().get( userRole).toRepresentation();
        userResource.roles().clientLevel(clientRepresentation.getId() ).add( Arrays.asList(roleRepresentation) );
    }

    private String getClientUUID() {
        return realmResource.clients().findByClientId(clientID).get(0).getId();
    }

    private Response createUser(String username) {
        return realmResource.users().create(createUserRepresentation(username));
    }
    private UserResource getUser(String userId) {
        return realmResource.users().get(userId);
    }

    private RoleRepresentation getRole(String roleToAdd) {
        return getClientResource().roles().get(roleToAdd).toRepresentation();
    }
    private ClientResource getClientResource() {
        return realmResource.clients().get(getClientUUID());
    }


    private UserRepresentation createUserRepresentation(String username) {
        UserRepresentation user = new UserRepresentation();
        user.setUsername(username);
        user.setEnabled(true);
        return user;
    }



}
