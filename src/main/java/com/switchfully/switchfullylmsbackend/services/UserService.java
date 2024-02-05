package com.switchfully.switchfullylmsbackend.services;

import com.switchfully.switchfullylmsbackend.dtos.users.CreateStudentDto;
import com.switchfully.switchfullylmsbackend.dtos.users.StudentDto;
import com.switchfully.switchfullylmsbackend.dtos.users.UpdateUserDto;
import com.switchfully.switchfullylmsbackend.entities.AbstractUser;
import com.switchfully.switchfullylmsbackend.dtos.users.UserDto;
import com.switchfully.switchfullylmsbackend.entities.Coach;
import com.switchfully.switchfullylmsbackend.entities.Student;
import com.switchfully.switchfullylmsbackend.exceptions.*;

import com.switchfully.switchfullylmsbackend.mappers.StudentMapper;
import com.switchfully.switchfullylmsbackend.mappers.UserMapper;
import com.switchfully.switchfullylmsbackend.repositories.StudentRepository;
import com.switchfully.switchfullylmsbackend.repositories.UserRepository;
import org.json.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import org.keycloak.TokenVerifier;
import org.keycloak.common.VerificationException;
import org.keycloak.representations.AccessToken;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final StudentMapper studentMapper;
    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(StudentMapper studentMapper, StudentRepository studentRepository, UserRepository userRepository, UserMapper userMapper) {
        this.studentMapper = studentMapper;
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public StudentDto createStudent(CreateStudentDto createStudentDto, String createdUserId) {
        return studentMapper.mapStudentToStudentDto(
                studentRepository.save(
                        studentMapper.mapCreateUserDtoToStudent(createStudentDto, createdUserId)
                )
        );
    }

    public UserDto updateUser(AbstractUser abstractUser, UpdateUserDto updateUserDto) {
        abstractUser.setDisplayName(updateUserDto.getDisplayName());

        return userMapper.mapAbstractUserToUserDto(userRepository.save(abstractUser), abstractUser.getRole());
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


    public AbstractUser getUserByToken(String bearerToken) {
        String[] chunks = bearerToken.split("\\.");
        JSONObject payload = new JSONObject(decode(chunks[1]));

        return userRepository.findByEmail(payload.get("preferred_username").toString());
    }

    public UserDto getUserDtoByToken(String bearerToken) {
        AbstractUser user = getUserByToken(bearerToken);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<String> roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).toList();

        return userMapper.mapAbstractUserToUserDto(user, roles.get(0));
    }

    public Student getStudentByToken(String bearerToken) {
        AbstractUser abstractUser = this.getUserByToken(bearerToken);
        if (abstractUser instanceof Student) {
            return (Student) abstractUser;
        } else {
            throw new NotAStudentException();
        }
    }

    public Coach getCoachByToken(String bearerToken) {
        AbstractUser abstractUser = this.getUserByToken(bearerToken);
        if (abstractUser instanceof Coach) {
            return (Coach) abstractUser;
        } else {
            throw new NotACoachException();
        }
    }

    public static String decode(String encodedString) {
        return new String(Base64.getUrlDecoder().decode(encodedString));
    }
    
    public boolean validateToken(String token) {
        try {
            AccessToken accessToken = TokenVerifier.create(token, AccessToken.class).getToken();
            return true;
        } catch (VerificationException e) {
            return false;
        }
    }

    public List<StudentDto> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(studentMapper::mapStudentToStudentDto)
                .collect(Collectors.toList());
    }
}
