package com.freitas.lucas.carregistration.error;

import com.freitas.lucas.carregistration.error.exceptions.ObjectAlreadyExistsException;
import com.freitas.lucas.carregistration.error.exceptions.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler(ObjectAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> objectAlreadyExists(ObjectAlreadyExistsException e, HttpServletRequest request) {
        ErrorResponse err = new ErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(), null);
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<ErrorResponse> objectNotFoundException(ObjectNotFoundException e, HttpServletRequest request) {
        ErrorResponse err = new ErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), null);
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }
}
