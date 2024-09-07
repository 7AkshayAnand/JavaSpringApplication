package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.controllers;

import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.dto.EmployeeDTO;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.entities.EmployeeEntity;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.repositories.EmployeeRepository;
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

    private final EmployeeRepository  employeeRepository;
    public EmployeeController(EmployeeRepository employeeRepository){
        this.employeeRepository=employeeRepository;
    }
     @GetMapping("{employeeId}")
     public EmployeeEntity getEmployeeById(@PathVariable Long employeeId){
//         return new EmployeeDTO(employeeId,"Anuj","anuj@gmail.com",27, LocalDate.of(2024,1,3),true);
      return employeeRepository.findById(employeeId).orElse(null);
     }

     @GetMapping()
     public List<EmployeeEntity> getAllEmployee(@RequestParam(required=false) Integer age, @RequestParam(required = false) String sortby){
//          return "hi my age is "+age+" and sorting is "+sortby;
         return employeeRepository.findAll();
     }
//     @PostMapping()
//     public EmployeeDTO createEmployee(@RequestBody EmployeeDTO inputEmployee){
//         inputEmployee.setId(100L);
//         return inputEmployee;
//     }
    @PostMapping()
    public EmployeeEntity createEmployee(@RequestBody EmployeeEntity inputEmployee){

        return employeeRepository.save(inputEmployee);
    }
     @PutMapping
    public String updateEmployee(){
         return "updation successfull";
     }

}
