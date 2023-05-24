package com.api.maximumNumber.service;

import com.api.maximumNumber.constant.ConstantMessages;
import com.api.maximumNumber.models.CalculationResponse;
import org.springframework.stereotype.Service;

import java.util.stream.IntStream;

@Service
public class CalculationServiceImpl implements CalculationService {

   @Override
   public CalculationResponse calculateMaximum(int divisor, int remainder, int number) {

      int response = IntStream.rangeClosed(0, number)
          .filter(k -> k % divisor == remainder)
          .max()
          .orElseThrow(() -> new IllegalArgumentException(ConstantMessages.CALCULATION_ERROR));

      return new CalculationResponse(response);
   }
}
