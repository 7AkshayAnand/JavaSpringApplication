package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.controllers;

import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.dto.DepartmentDTO;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.dto.EmployeeDTO;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.exceptions.ResourceNotFoundException;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.services.DepartmentService;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }



    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAllDepartment(){
      return ResponseEntity.ok(departmentService.getAllDepartment());
    }

    @GetMapping("{departmentId}")
    public ResponseEntity<DepartmentDTO> getEmployeeById(@PathVariable Long departmentId){
        Optional<DepartmentDTO> departmentDTO=departmentService.getDepartmentById(departmentId);
        return departmentDTO.map(departmentDTO1 ->  ResponseEntity.ok(departmentDTO1))
                .orElseThrow(()-> new ResourceNotFoundException("Department not found"));

    }






    @PostMapping
    public ResponseEntity<DepartmentDTO> createNewDepartment(@RequestBody DepartmentDTO inputDepartment){
        DepartmentDTO savedDepartment=departmentService.createNewDepartment(inputDepartment);
        return new ResponseEntity<>(savedDepartment,HttpStatus.CREATED);
    }




    @PutMapping(path="/{departmentId}")
       public ResponseEntity<DepartmentDTO> updateDepartmentById(@RequestBody DepartmentDTO departmentDTO,@PathVariable Long departmentId){
        return ResponseEntity.ok(departmentService.updateDepartmentById(departmentDTO,departmentId));
    }


    @DeleteMapping(path="/{departmentId}")
    public ResponseEntity<Boolean> deleteDepartmentById(@PathVariable Long departmentId){
     boolean gotDeleted=departmentService.deleteDepartmentById(departmentId);
     if(gotDeleted) return ResponseEntity.ok(true);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
    }



}
