package com.example.demo.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsuarioNullException.class)
    public String handleUsuarioNullException() {
        return "redirect:/imcalculator/login";
    }
}
