package com.api.maximumNumber.controller;

import com.api.maximumNumber.models.CalculationRequest;
import com.api.maximumNumber.models.CalculationResponse;
import com.api.maximumNumber.service.CalculationServiceImpl;
import com.api.maximumNumber.validation.CalculationValidator;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/maximum")
@AllArgsConstructor
public class CalculationController {

   private final CalculationServiceImpl service;
   private final CalculationValidator validator;

   @GetMapping
   public ResponseEntity<CalculationResponse> calculateMaximum(
       @RequestParam int divisor,
       @RequestParam int remainder,
       @RequestParam int number) {

      validator.validateNumbersArePositive(divisor, remainder, number);

      CalculationResponse response = this.service.calculateMaximum(divisor, remainder, number);

      return ResponseEntity.ok(response);
   }

   @PostMapping
   public ResponseEntity<CalculationResponse> calculateMaximum(@RequestBody CalculationRequest request) {

      validator.validateNumbersArePositive(request.getDivisor(), request.getRemainder(), request.getNumber());

      CalculationResponse response = this.service.calculateMaximum(
          request.getDivisor(),
          request.getRemainder(),
          request.getNumber()
      );

      return ResponseEntity.ok(response);
   }
}