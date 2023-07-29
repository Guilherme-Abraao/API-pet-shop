package com.example.petshop.exception;

public class HorarioJaAgendadoException extends RuntimeException {

    public HorarioJaAgendadoException(String message) {
        super(message);
    }

    public HorarioJaAgendadoException(String message, Throwable cause) {
        super(message, cause);
    }
}

