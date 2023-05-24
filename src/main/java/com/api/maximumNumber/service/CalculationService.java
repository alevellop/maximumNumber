package com.api.maximumNumber.service;

import com.api.maximumNumber.models.CalculationResponse;

public interface CalculationService {

   public CalculationResponse calculateMaximum(int divisor, int remainder, int number);

}