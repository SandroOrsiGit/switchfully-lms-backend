package com.switchfully.switchfullylmsbackend.exceptions;


public class CourseNotFoundException extends RuntimeException{
    public CourseNotFoundException() {
        super("Course not found");
    }
}
