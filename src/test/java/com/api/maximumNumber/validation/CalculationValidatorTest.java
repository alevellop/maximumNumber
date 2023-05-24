package com.api.maximumNumber.validation;

import com.api.maximumNumber.constant.ConstantMessages;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest
@ContextConfiguration(classes = {CalculationValidator.class})
class CalculationValidatorTest {

   private CalculationValidator validator = new CalculationValidator();

   @Test
   @DisplayName("Validate numbers with valid input parameters")
   void validateNumnbersWithValidParameters() {

      assertDoesNotThrow(() -> validator.validateNumbersArePositive(10, 5, 187));
   }

   @Test
   @DisplayName("Validate numbers with divisor less than 2")
   void validateNumbersWithDivisorLessThanTwo() {
      IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
          () -> validator.validateNumbersArePositive(-2, 5, 187));

      assertEquals(ConstantMessages.DIVISOR_ERROR, exception.getMessage());
   }

   @Test
   @DisplayName("Validate numbers with remainder out of range")
   void validateNumbersWithRemainderOutOfRange() {

      IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
          () -> validator.validateNumbersArePositive(10, -5, 187));

      assertEquals(ConstantMessages.REMAINDER_ERROR, exception.getMessage());
   }

   @Test
   @DisplayName("Validate numbers with number less than remainder")
   void validateNumbersWithNumberLessThanRemainder() {

      IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
          () -> validator.validateNumbersArePositive(10, 5, -5));

      assertEquals(ConstantMessages.NUMBER_ERROR, exception.getMessage());
   }
}
