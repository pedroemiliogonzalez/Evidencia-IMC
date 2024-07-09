package com.example.demo.exceptions;

public class UsuarioAlreadyExistsException extends RuntimeException {

    public UsuarioAlreadyExistsException(String message) {
        super(message);
    }

    public void printErrorMessage() {

        System.err.println(getMessage());
    }
}