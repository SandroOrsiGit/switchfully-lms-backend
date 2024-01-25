package com.switchfully.switchfullylmsbackend.exceptions;

public class NotAPartOfThisCourseException extends RuntimeException{
    public NotAPartOfThisCourseException() {
        super("User is not part of this course");
    }
}
