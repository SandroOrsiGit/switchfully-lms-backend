package com.switchfully.switchfullylmsbackend.exceptions;

import net.minidev.json.*;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException() {
        super("Email already exists");
    }
}
