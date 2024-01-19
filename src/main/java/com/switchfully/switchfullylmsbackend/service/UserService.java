package com.switchfully.switchfullylmsbackend.service;

import com.switchfully.switchfullylmsbackend.dto.user.CreateUserDto;
import com.switchfully.switchfullylmsbackend.dto.user.UserDto;
import com.switchfully.switchfullylmsbackend.entity.AbstractUser;
import com.switchfully.switchfullylmsbackend.entity.Student;
import com.switchfully.switchfullylmsbackend.mapper.StudentMapper;
import com.switchfully.switchfullylmsbackend.mapper.UserMapper;
import com.switchfully.switchfullylmsbackend.repository.UserRepository;
import org.json.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final StudentMapper studentMapper;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    public UserService(StudentMapper studentMapper, UserRepository userRepository, UserMapper userMapper) {
        this.studentMapper = studentMapper;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public void addUser(CreateUserDto createUserDto) {
        Student student = studentMapper.mapCreateUserDtoToStudent(createUserDto);
        userRepository.save(student);
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

    private static String decode(String encodedString) {
        return new String(Base64.getUrlDecoder().decode(encodedString));
    }
}
