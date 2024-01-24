package com.switchfully.switchfullylmsbackend.exceptions;

public class InvalidRoleException extends RuntimeException {
   public InvalidRoleException(String message){
      super(message);
   }
}