package com.switchfully.switchfullylmsbackend.exceptions;

public class InvalidRoleException extends IllegalArgumentException {
	public InvalidRoleException(String message){
		super(message);
	}
}
