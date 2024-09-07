package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.controllers;

import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.dto.EmployeeDTO;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.entities.EmployeeEntity;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.repositories.EmployeeRepository;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path="employees")
public class EmployeeController {
//    @GetMapping(path="/getMessage")
//    public String getMsg(){
//        return "Secret has been revealed and the Message is HELLO WORLD!!";
//    }

    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService){
        this.employeeService=employeeService;
    }

     @GetMapping("{employeeId}")
     public EmployeeDTO getEmployeeById(@PathVariable Long employeeId){
         return employeeService.getEmployeeById(employeeId);

     }
    @GetMapping
    public List<EmployeeDTO> getAllEmployee(@RequestParam(required = false) Integer age,@RequestParam(required = false) String sortBy){
        return employeeService.getAllEmployee();
    }

    @PostMapping()
    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO inputEmployee){
        return employeeService.createNewEmployee(inputEmployee);
    }



}
