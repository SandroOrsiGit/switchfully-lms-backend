package com.switchfully.switchfullylmsbackend.dtos.users;
public class UserDto {

	private final Long id;
	private final String email;
	private final String displayName;
	private final String role;

	public UserDto(Long id, String email, String displayName, String role) {
		this.id = id;
		this.email = email;
		this.displayName = displayName;
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getDisplayName() {
		return displayName;
	}

	public String getRole() {
		return role;
	}
}
