package com.switchfully.switchfullylmsbackend.mapper;

import com.switchfully.switchfullylmsbackend.dto.user.UserDto;
import com.switchfully.switchfullylmsbackend.entity.AbstractUser;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
	public UserDto mapAbstractUserToUserDto(AbstractUser abstractUser, String role) {
		return new UserDto(abstractUser.getId(), abstractUser.getEmail(), abstractUser.getDisplayName(), role);
	}
}
