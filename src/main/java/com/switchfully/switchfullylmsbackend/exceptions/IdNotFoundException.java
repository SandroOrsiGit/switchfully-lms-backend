package com.switchfully.switchfullylmsbackend.exceptions;

public class IdNotFoundException extends IllegalArgumentException{
	public IdNotFoundException(String message){
		super(message);
	}
}
