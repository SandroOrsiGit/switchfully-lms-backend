package com.switchfully.switchfullylmsbackend.mappers;

import com.switchfully.switchfullylmsbackend.dtos.users.UserDto;
import com.switchfully.switchfullylmsbackend.entities.AbstractUser;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
	public UserDto mapAbstractUserToUserDto(AbstractUser abstractUser, String role) {
		return new UserDto(abstractUser.getId(), abstractUser.getEmail(), abstractUser.getDisplayName(), role);
	}
}
