package com.switchfully.switchfullylmsbackend.services;

import com.switchfully.switchfullylmsbackend.dtos.users.CreateStudentDto;
import com.switchfully.switchfullylmsbackend.dtos.users.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserServiceTest {
    @Autowired UserService userService;

    @Test
    void whenCreateUser_thenUserIsCreated() {
        // given
        CreateStudentDto createStudentDto = new CreateStudentDto(
                "testDisplayName",
                "test@mail.com",
                "password"
        );

        // when
        UserDto userDto = userService.addUser(createStudentDto);
    }
}
