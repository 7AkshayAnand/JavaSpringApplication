package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.Annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Constraint(validatedBy = {EmployeeRoleValidator.class})
public @interface EmployeeRoleValidation {
    String message() default "Role of employee can be USER OR ADMIN ONLY";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
