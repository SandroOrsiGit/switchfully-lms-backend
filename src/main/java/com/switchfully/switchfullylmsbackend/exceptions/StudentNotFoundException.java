package com.switchfully.switchfullylmsbackend.exceptions;

public class StudentNotFoundException extends RuntimeException{

    public StudentNotFoundException() {
        super("This user doesn't exist");
    }

    public StudentNotFoundException(String message) {
        super(message);
    }
}
