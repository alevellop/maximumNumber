package com.api.maximumNumber.service;

import com.api.maximumNumber.models.CalculationResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@WebMvcTest
@ContextConfiguration(classes = {CalculationServiceImpl.class})
class CalculationServiceImplTest {

   private CalculationServiceImpl service = new CalculationServiceImpl();

   @Test
   @DisplayName("Calculate maximum with valid input parameters")
   void calculateMaximumWithValidInputParameters() {

      int divisor = 10;
      int remainder = 5;
      int number = 187;
      int result = 185;

      CalculationResponse expectedResponse = service.calculateMaximum(divisor, remainder, number);

      assertEquals(result, expectedResponse.getResult());
   }

   @Test
   @DisplayName("Calculate maximum with no valid parameters")
   void calculateMaximumWithNoValidParameters() {
      int divisor = -1;
      int remainder = -1;
      int number = -1;

      assertThrows(IllegalArgumentException.class, () ->
          service.calculateMaximum(divisor, remainder, number));
   }
}