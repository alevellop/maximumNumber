package com.api.maximumNumber.config;

import com.api.maximumNumber.validation.CalculationValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CalculationConfig {

   @Bean
   public CalculationValidator calculationValidator() {
      return new CalculationValidator();
   }
}
