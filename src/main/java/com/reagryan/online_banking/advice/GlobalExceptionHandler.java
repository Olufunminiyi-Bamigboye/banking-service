package com.reagryan.online_banking.advice;

import com.reagryan.online_banking.dto.response.ErrorDetail;
import com.reagryan.online_banking.exception.InsufficientAmountException;
import com.reagryan.online_banking.exception.InvalidAmountException;
import com.reagryan.online_banking.exception.InvalidUserException;
import com.reagryan.online_banking.exception.CustomerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorDetail> handleUserNotFoundException(CustomerNotFoundException ex, WebRequest request){
        ErrorDetail errorDetail = new ErrorDetail(ex.getMessage(), LocalDateTime.now(), request.getDescription(false));

        return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidUserException.class)
    public ResponseEntity<ErrorDetail> handleInvalidUserException(InvalidUserException ex, WebRequest request){
        ErrorDetail errorDetail = new ErrorDetail(ex.getMessage(), LocalDateTime.now(), request.getDescription(false));

        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(InvalidAmountException.class)
    public ResponseEntity<ErrorDetail> handleInvalidAmountException(InvalidAmountException ex, WebRequest request){
        ErrorDetail errorDetail = new ErrorDetail(ex.getMessage(), LocalDateTime.now(), request.getDescription(false));

        return new ResponseEntity<>(errorDetail, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(InsufficientAmountException.class)
    public ResponseEntity<ErrorDetail> handleInsufficientAmountException(InsufficientAmountException ex, WebRequest request){
        ErrorDetail errorDetail = new ErrorDetail(ex.getMessage(), LocalDateTime.now(), request.getDescription(false));

        return new ResponseEntity<>(errorDetail, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
