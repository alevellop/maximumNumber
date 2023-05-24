package com.api.maximumNumber.controller;

import com.api.maximumNumber.models.CalculationRequest;
import com.api.maximumNumber.models.CalculationResponse;
import com.api.maximumNumber.service.CalculationServiceImpl;
import com.api.maximumNumber.validation.CalculationValidator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ContextConfiguration(classes = {CalculationController.class})
class CalculationControllerTest {

   @Autowired
   private MockMvc mockMvc;

   @MockBean
   private CalculationServiceImpl serviceMock;

   @MockBean
   private CalculationValidator validatorMock;

   private CalculationController controller;

   private int divisor = 10;
   private int remainder = 5;
   private int number = 187;
   private int result = 185;

   @Test
   @DisplayName("Calculate maximum number with valid path variable in Get method")
   void calculateMaximumNumber_withValidPathVariable_shouldReturnOk() throws Exception {

      CalculationResponse expectedResponse = new CalculationResponse(result);

      given(serviceMock.calculateMaximum(anyInt(), anyInt(), anyInt())).willReturn(expectedResponse);

      mockMvc.perform(get("/api/maximum")
              .param("divisor", String.valueOf(divisor))
              .param("remainder", String.valueOf(remainder))
              .param("number", String.valueOf(number)))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.result").value(result));

      verify(validatorMock).validateNumbersArePositive(divisor, remainder, number);
      verify(serviceMock).calculateMaximum(divisor, remainder, number);
   }

   @Test
   @DisplayName("Calculate maximum number without path variable in Get method should throw IllegalArgumentException")
   void calculateMaximumNumber_withoutValidPathVariable_shouldThrowIllegalArgumentException() throws Exception {

      mockMvc.perform(get("/api/maximum")
              .param("remainder", String.valueOf(remainder))
              .param("number", String.valueOf(number)))
          .andExpect(status().isBadRequest())
          .andExpect(expected -> assertTrue(expected.getResolvedException() instanceof Exception))
          .andExpect(expected ->
              assertEquals("Required request parameter 'divisor' for method parameter type int is not present",
                  expected.getResolvedException().getMessage()));
   }

   @Test
   @DisplayName("Calculate maximum number with valid request body in Post method")
   void calculateMaximumNumber_withValidRequestBody_shouldReturnOk() throws Exception {

      CalculationRequest request = new CalculationRequest(divisor, remainder, number);
      String requestJsonString = objectoToJsonString(request);

      CalculationResponse expectedResponse = new CalculationResponse(result);

      doNothing().when(validatorMock).validateNumbersArePositive(anyInt(), anyInt(), anyInt());
      given(serviceMock.calculateMaximum(anyInt(), anyInt(), anyInt())).willReturn(expectedResponse);

      mockMvc.perform(post("/api/maximum")
              .contentType("application/json")
              .content(requestJsonString))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.result").value(result));

      verify(validatorMock).validateNumbersArePositive(divisor, remainder, number);
      verify(serviceMock).calculateMaximum(divisor, remainder, number);
   }

   @Test
   @DisplayName("Calculate maximum number without request body in Post method should throw IllegalArgumentException")
   void calculateMaximumNumber_withoutRequestBody_shouldThrowIllegalArgumentException() throws Exception {

      mockMvc.perform(post("/api/maximum")
              .contentType("application/json"))
          .andExpect(status().isBadRequest())
          .andExpect(expected -> assertTrue(expected.getResolvedException() instanceof HttpMessageNotReadableException))
          .andExpect(expected ->
              assertEquals("Required request body is missing: public org.springframework.http.ResponseEntity<com.api.maximumNumber.models.CalculationResponse> com.api.maximumNumber.controller.CalculationController.calculateMaximum(com.api.maximumNumber.models.CalculationRequest)",
                  expected.getResolvedException().getMessage()));
   }

   private String objectoToJsonString(CalculationRequest request) throws JsonProcessingException {

      return new ObjectMapper().writeValueAsString(request);
   }
}
