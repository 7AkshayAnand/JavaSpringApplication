package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private Long id;
   @NotBlank(message="Required field in employee : Name")
   @NotEmpty(message = "name cannot be empty")
   @Size(min=3, max=10, message="number of character should be in range")
    private String name;
   @Email(message = "email should be a valid email")
    private String email;

   @Max(value=80,message = "age cannot be > than 80")
   @Min(value=18,message = "age of employee cannot be <18")
    private Integer age;


   @Positive(message = "salary cannot be negative")
   @Digits(integer = 6,fraction = 2,message = "salary can be in given form")
   @DecimalMax(value="10000.99")
   @DecimalMin(value="100.50")
   private Double salary;

   @NotBlank(message="Role cannot be null")
   @Pattern(regexp="^(ADMIN|USER)",message = "role of employee can be user or admin")
   private String role;//Admin,user

    @PastOrPresent(message = "date of joining cannot be in future")
    private LocalDate dateOfJoining;

    @AssertTrue(message = "Employee should be active")
    @JsonProperty("isActive")
    private Boolean isActive;


}




