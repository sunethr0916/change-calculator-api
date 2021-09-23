package com.adp.sample.demo.exception;

import com.adp.sample.demo.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import javax.validation.ConstraintViolationException;
import java.util.Arrays;


@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler
{
    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException e) {
        return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getMessage(), Arrays.asList(e.getLocalizedMessage())), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getMessage(), Arrays.asList(e.getLocalizedMessage())), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
