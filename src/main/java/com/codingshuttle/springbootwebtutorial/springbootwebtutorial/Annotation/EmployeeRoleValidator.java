package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.Annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.*;

public class EmployeeRoleValidator implements ConstraintValidator<EmployeeRoleValidation,String>{

    @Override
    public boolean isValid(String inputRole, ConstraintValidatorContext constraintValidatorContext){
        List<String> roles=List.of("USER","ADMIN");
        return roles.contains(inputRole);
    }
}
