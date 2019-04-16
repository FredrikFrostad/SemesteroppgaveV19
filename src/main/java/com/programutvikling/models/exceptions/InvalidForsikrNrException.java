package com.programutvikling.models.exceptions;

public class InvalidForsikrNrException extends InvalidNumberFormatException {

    public InvalidForsikrNrException(String message) {
        super(message);
    }
}
