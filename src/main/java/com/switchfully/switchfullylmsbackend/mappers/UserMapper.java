package com.switchfully.switchfullylmsbackend.mappers;

import com.switchfully.switchfullylmsbackend.dtos.courses.CourseDto;
import com.switchfully.switchfullylmsbackend.dtos.users.StudentWithCoursesDto;
import com.switchfully.switchfullylmsbackend.dtos.users.UserDto;
import com.switchfully.switchfullylmsbackend.entities.AbstractUser;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {
	public UserDto mapAbstractUserToUserDto(AbstractUser abstractUser, String role) {
		return new UserDto(abstractUser.getId(), abstractUser.getEmail(), abstractUser.getDisplayName(), role);
	}

	public StudentWithCoursesDto mapAbstractUserToStudentWithCoursesDto(AbstractUser abstractUser, List<CourseDto> courseDtoList) {
		return new StudentWithCoursesDto(abstractUser.getId(), abstractUser.getEmail(), abstractUser.getDisplayName(), courseDtoList);
	}
}
