package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.advices;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

//The @Data annotation generates common methods for a class, which can reduce boilerplate code and make your code cleaner and more maintainable. Specifically, @Data automatically generates:
//
//Getters and Setters:
//
//For all fields in the class. You don't need to manually write get and set methods.
//toString() Method:
//
//Provides a toString() method that includes the class name and all fields, which is useful for debugging.
//equals() and hashCode() Methods:
//
//Generates equals() and hashCode() methods based on all fields. This is particularly useful for comparing instances of the class and using instances in collections that rely on hashing.

//The @Builder annotation from Lombok is used to implement the Builder pattern for your class. The Builder pattern is a design pattern that allows for the creation of complex objects step-by-step, providing a flexible way to construct objects without requiring a large number of constructor parameters.
@Data
@Builder
public class APIError {
    private HttpStatus status;
    private String message;
}
