package com.switchfully.switchfullylmsbackend.exceptions;

public class ModuleNotFoundException extends RuntimeException{
    public ModuleNotFoundException() {
        super("Module not found");
    }
}
