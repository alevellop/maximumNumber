# API maximumNumber
API with Java 11 and JUnit on Spring Boot to calculate a maximum number

## Description
You are given three integers x, y, and n. Your task is to find the maximum integer k such that 0 ≤ k ≤ n and k mod x = y, where mod is the modulo operation. Many programming languages use the percent operator % to implement it.

In other words, given x, y, and n, you need to find the maximum possible integer from 0 to n that has the remainder y modulo x.

You have to answer t independent test cases. It is guaranteed that such k exists for each test case.

#### Input:

The first line of the input contains one integer t (1 ≤ t ≤ 5⋅10^4) — the number of test cases. The next t lines contain the test cases.

The only line of each test case contains three integers x, y, and n (2 ≤ x ≤ 10^9; 0 ≤ y < x; y ≤ n ≤ 10^9).

It can be shown that such k always exists under the given constraints.

#### Output:

For each test case, print the answer — the maximum non-negative integer k such that 0 ≤ k ≤ n and k mod x = y. It is guaranteed that the answer always exists.

## Used technology versions:
- Java 11
- Spring Boot 2.7.13-SNAPSHOT
- JUnit 5.8.2

## Http connections:
 This proyect has two kind of http connection: GET and POST
 c GET connection has the following format:
 ``http://localhost:8080/api/maximum?divisor=x&remainder=y&number=n``
 
 #### POST connection has the following format:
 ``http://localhost:8080/api/maximum`` And send a JSON request with this format: ``{"divisor": x,"remainder": y,"number": n}``
 
 Both connections return the result of calculating the maximum number following the statement of the exercise, with JSON format: ``{"result": k}``
 
 ## Details:
 For this exercise are developed this packages:
 - Controller: contains the controller that performs the http connection to transmit parameters.
 - Service: contains the class that performs the calculation, and its contract definition with an interface.
 - Model: where the basic input parameter model and the output parameter model are defined. 
 - Validator: contains the class in charge of validating whether the parameters entered are valid to perform the calculation.  
 - Exception manager: contains the class in charge of managing the different errors that may occur when executing the API, and displays a customised message for the cases taken into account. 
 - Constant: contains a class where the different messages used in the management of exceptions are stored.

Also the necessary tests have  been implemented for the classes that need them and stored in 'maximumNumber/src/test/java/com/api/maximumNumber/', ensuring a minimum of 80% of the code of the whole project is tested.

## How to execute:
#### API:
Depending on the IDE used for execution, the process may vary somewhat. Although usually (and with IntelliJ) it is enough to select the main class 'MaximumNumberApplication' and select the 'execute' option.

#### Tests:
To run the developed tests and see the result, you can follow a similar process to the previous one, and as mentioned before, it depends on the IDE used. However, in IntelliJ it is necessary to select the root folder of the project in the IDE explorer and execute the option 'Run All Tests'.

#### Postman:
Inside the 'resource' folder you can find the 'Postman' folder with a JSON file, containing tests defined to run in Postman.
