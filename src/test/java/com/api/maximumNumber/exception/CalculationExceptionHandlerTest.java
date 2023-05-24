package com.api.maximumNumber.exception;

import com.api.maximumNumber.constant.ConstantMessages;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.bind.MissingServletRequestParameterException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@WebMvcTest
@ContextConfiguration(classes = {CalculationExceptionHandler.class})
class CalculationExceptionHandlerTest {

   private CalculationExceptionHandler exceptionHandler = new CalculationExceptionHandler();

   @Test
   @DisplayName("Handle IllegalArgumentException")
   void testHandleIllegalArgumentException() {
      IllegalArgumentException exception = new IllegalArgumentException("Invalid parameters");

      ResponseEntity<ErrorResponse> response = exceptionHandler.handleException(exception);

      assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
      assertEquals("Invalid parameters", response.getBody().getErrorMessage());
   }

   @Test
   @DisplayName("Handle MissingServletRequestParameterException")
   void testHandleMissingServletRequestParameterException() {
      MissingServletRequestParameterException exception = new MissingServletRequestParameterException("divisor", "String");

      ResponseEntity<ErrorResponse> response = exceptionHandler.handleException(exception);

      assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
      assertEquals("Required request parameter 'divisor' for method parameter type String is not present",
          response.getBody().getErrorMessage());
   }

   @Test
   @DisplayName("Handle HttpMessageNotReadableException")
   void testHandleHttpMessageNotReadableException() {
      HttpMessageNotReadableException exception = new HttpMessageNotReadableException(ConstantMessages.REQUEST_BODY_NULL);

      ResponseEntity<ErrorResponse> response = exceptionHandler.handleHttpMessageNotReadableException(exception);

      assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
      assertEquals(ConstantMessages.REQUEST_BODY_NULL, response.getBody().getErrorMessage());
   }
}
