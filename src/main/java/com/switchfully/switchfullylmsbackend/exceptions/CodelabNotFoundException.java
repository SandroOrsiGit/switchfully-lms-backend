package com.switchfully.switchfullylmsbackend.exceptions;

public class CodelabNotFoundException extends RuntimeException {

    public CodelabNotFoundException() {
        super("Codelab not found");
    }

}
