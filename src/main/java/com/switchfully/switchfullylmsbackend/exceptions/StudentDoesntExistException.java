package com.switchfully.switchfullylmsbackend.exceptions;

public class StudentDoesntExistException extends RuntimeException{

    public StudentDoesntExistException () {
        super("This user doesn't exist");
    }

    public StudentDoesntExistException (String message) {
        super(message);
    }
}
