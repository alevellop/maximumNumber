package com.api.maximumNumber.constant;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ConstantMessages {

   public static final String DIVISOR_ERROR = "Divisor must be greather than or equal to two.";
   public static final String REMAINDER_ERROR = "Remainder must be greather than or equal to zero, and less than divisor.";
   public static final String NUMBER_ERROR = "Number must be grather than or equal to remainder.";
   public static final String CALCULATION_ERROR = "Arguments with wrong format or value.";
   public static final String REQUEST_BODY_NULL = "Required request parameter is not present. Format allowed: '{ divisor: 2, remainder: 4, number: 50}'";
}
