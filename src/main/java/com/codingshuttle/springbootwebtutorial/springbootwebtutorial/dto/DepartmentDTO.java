package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.dto;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.Annotation.EmployeeRoleValidation;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {

    private Integer id;
    private String title;
    @JsonProperty("isActive")
    private boolean isActive;
    private LocalDate createdAt;
}
