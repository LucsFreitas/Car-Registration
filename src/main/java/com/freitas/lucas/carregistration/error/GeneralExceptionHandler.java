package com.freitas.lucas.carregistration.error;

import com.freitas.lucas.carregistration.error.exceptions.ForbiddenException;
import com.freitas.lucas.carregistration.error.exceptions.ObjectAlreadyExistsException;
import com.freitas.lucas.carregistration.error.exceptions.ObjectNotFoundException;
import com.freitas.lucas.carregistration.error.exceptions.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GeneralExceptionHandler {

    private ResponseEntity<ErrorResponse> createResponse(HttpStatus httpStatus, String message) {
        ErrorResponse err = new ErrorResponse(message, httpStatus.value(), httpStatus.name(), null);
        return new ResponseEntity<>(err, httpStatus);
    }

    @ExceptionHandler(ObjectAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> objectAlreadyExists(ObjectAlreadyExistsException e, HttpServletRequest request) {
        return createResponse(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<ErrorResponse> objectNotFoundException(ObjectNotFoundException e, HttpServletRequest request) {
        return createResponse(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ErrorResponse> forbiddenException(ForbiddenException e, HttpServletRequest request) {
        return createResponse(HttpStatus.FORBIDDEN, e.getMessage());
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResponse> unauthorizedException(UnauthorizedException e, HttpServletRequest request) {
        return createResponse(HttpStatus.UNAUTHORIZED, e.getMessage());
    }
}
