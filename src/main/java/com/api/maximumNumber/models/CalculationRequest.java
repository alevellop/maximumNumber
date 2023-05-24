package com.api.maximumNumber.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CalculationRequest {

   private int divisor;
   private int remainder;
   private int number;
}