package com.freitas.lucas.carregistration.error;

import java.util.List;

public class ErrorResponse {
    private final String message;
    private final int errorCode;
    private final String status;
    private final List<ErrorObject> errors;

    public String getMessage() {
        return message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getStatus() {
        return status;
    }

    public List<ErrorObject> getErrors() {
        return errors;
    }

    public ErrorResponse(String message, int errorCode, String status, List<ErrorObject> errors) {
        this.message = message;
        this.errorCode = errorCode;
        this.status = status;
        this.errors = errors;
    }
}
