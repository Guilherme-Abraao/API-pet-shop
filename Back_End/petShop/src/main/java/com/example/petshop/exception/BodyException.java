package com.example.petshop.exception;

public class BodyException extends RuntimeException {

        public BodyException(String message) {
            super(message);
        }

        public BodyException(String message, Throwable cause) {
            super(message, cause);
        }
}
