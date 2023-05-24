package com.api.maximumNumber.validation;

import com.api.maximumNumber.constant.ConstantMessages;

public class CalculationValidator {

   public void validateNumbersArePositive(int divisor, int remainder, int number) {

      if (divisor < 2)
         throw new IllegalArgumentException(ConstantMessages.DIVISOR_ERROR);

      if (remainder < 0 || remainder >= divisor)
         throw new IllegalArgumentException(ConstantMessages.REMAINDER_ERROR);

      if (number < remainder)
         throw new IllegalArgumentException(ConstantMessages.NUMBER_ERROR);
   }
}
