package com.switchfully.switchfullylmsbackend.exceptions;

public class NotAStudentException extends RuntimeException{
    public NotAStudentException() {
        super("User is not a student");
    }
}
