package com.switchfully.switchfullylmsbackend.exceptions;

public class NotACoachException extends RuntimeException{

    public NotACoachException() {
        super("User is not a coach");
    }

}
