package com.switchfully.switchfullylmsbackend.exception;

public class IdNotFoundException extends IllegalArgumentException{
	public IdNotFoundException(String message){
		super(message);
	}
}
