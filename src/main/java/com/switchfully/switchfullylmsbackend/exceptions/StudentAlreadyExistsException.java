package com.switchfully.switchfullylmsbackend.exceptions;

public class StudentAlreadyExistsException extends RuntimeException {
   public StudentAlreadyExistsException() {
      super("This student already exists");
   }
   
   public StudentAlreadyExistsException(String message) {
      super(message);
   }
}
