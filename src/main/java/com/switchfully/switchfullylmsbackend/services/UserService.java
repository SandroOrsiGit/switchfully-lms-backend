package com.switchfully.switchfullylmsbackend.services;

import com.switchfully.switchfullylmsbackend.dtos.users.CreateUserDto;
import com.switchfully.switchfullylmsbackend.dtos.users.UpdateUserDto;
import com.switchfully.switchfullylmsbackend.entities.AbstractUser;
import com.switchfully.switchfullylmsbackend.dtos.users.UserDto;
import com.switchfully.switchfullylmsbackend.entities.Student;
import com.switchfully.switchfullylmsbackend.exceptions.IdNotFoundException;
import com.switchfully.switchfullylmsbackend.mappers.StudentMapper;
import com.switchfully.switchfullylmsbackend.mappers.UserMapper;
import com.switchfully.switchfullylmsbackend.repositories.UserRepository;
import com.switchfully.switchfullylmsbackend.security.KeycloakService;
import org.json.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.Objects;
import java.util.Base64;
import java.util.List;

@Service
public class UserService {

    private final StudentMapper studentMapper;
    private final UserRepository userRepository;
    private final KeycloakService keycloakService;
    private final UserMapper userMapper;

    public UserService(StudentMapper studentMapper, UserRepository userRepository, KeycloakService keycloakService, UserMapper userMapper) {
        this.studentMapper = studentMapper;
        this.userRepository = userRepository;
        this.keycloakService = keycloakService;
        this.userMapper = userMapper;
    }

    public void addUser(CreateUserDto createUserDto) {
        Student student = studentMapper.mapCreateUserDtoToStudent(createUserDto);
        keycloakService.addUser(createUserDto);
        userRepository.save(student);
    }

    public void updateUser(UpdateUserDto updateUserDto) {
        AbstractUser emailUser = userRepository.findByEmail(updateUserDto.getEmail());
        if (emailUser != null && !Objects.equals(emailUser.getId(), updateUserDto.getId())) {
            throw new IllegalArgumentException("email already in use");
        }
        AbstractUser user = userRepository.findById(updateUserDto.getId()).orElseThrow(() -> new IllegalArgumentException("User not found"));
        user.setDisplayName(updateUserDto.getDisplayName());
        userRepository.save(user);
    }

    public UserDto getUserById(Long userId) {
        AbstractUser user = userRepository.findById(userId)
                .orElseThrow(() -> new IdNotFoundException("User with id " + userId + " not found"));
        String role = user.getRole();
        return userMapper.mapAbstractUserToUserDto(user, role);
    }

    public String getRoleByUserId(Long userId) {
        return getUserById(userId).getRole();
    }


    public UserDto getUserByToken(String bearerToken) {
        String[] chunks = bearerToken.split("\\.");
        JSONObject payload = new JSONObject(decode(chunks[1]));

        AbstractUser user = userRepository.findByEmail(payload.get("preferred_username").toString());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<String> roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).toList();

        return userMapper.mapAbstractUserToUserDto(user, roles.get(0));
    }

    protected static String decode(String encodedString) {
        return new String(Base64.getUrlDecoder().decode(encodedString));
    }
}
