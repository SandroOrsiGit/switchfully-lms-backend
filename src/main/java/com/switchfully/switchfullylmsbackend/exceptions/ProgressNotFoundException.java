package com.switchfully.switchfullylmsbackend.exceptions;

public class ProgressNotFoundException extends RuntimeException {

    public ProgressNotFoundException() {
        super("Progress not found");
    }

}
