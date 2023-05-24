package com.api.maximumNumber.exception;

import com.api.maximumNumber.constant.ConstantMessages;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CalculationExceptionHandler {

   @ExceptionHandler({IllegalArgumentException.class, MissingServletRequestParameterException.class})
   protected ResponseEntity<ErrorResponse> handleException(Exception ex) {

      return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);
   }

   @ExceptionHandler({HttpMessageNotReadableException.class})
   protected ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(Exception ex) {

      ErrorResponse errorResponse = new ErrorResponse(ConstantMessages.REQUEST_BODY_NULL);

      return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
   }
}