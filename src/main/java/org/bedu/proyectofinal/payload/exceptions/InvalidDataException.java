package org.bedu.proyectofinal.payload.exceptions;

public class InvalidDataException extends Exception {

    private static final long serialVersionUID = 1L;
    private String errorMessage;
    private int errorCode;

    public InvalidDataException(String errorMessage, int errorCode) {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
