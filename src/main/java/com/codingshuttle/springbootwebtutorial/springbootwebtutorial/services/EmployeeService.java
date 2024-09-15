package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.services;

import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.dto.EmployeeDTO;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.entities.EmployeeEntity;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.exceptions.ResourceNotFoundException;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;


    public EmployeeService(EmployeeRepository employeeRepository,ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper=modelMapper;
    }

    public Optional<EmployeeDTO> getEmployeeById(Long id){
//        EmployeeEntity employeeEntity=employeeRepository.findById(id).orElse(null);
//
//       return  modelMapper.map(employeeEntity, EmployeeDTO.class);

        return employeeRepository.findById(id).map(employeeEntity -> modelMapper.map(employeeEntity,EmployeeDTO.class));

    }
    public List<EmployeeDTO> getAllEmployee(){
        List<EmployeeEntity> employeeEntities=employeeRepository.findAll();

      return  employeeEntities
                .stream()
                .map(employeeEntity->modelMapper.map(employeeEntity,EmployeeDTO.class))
                .collect(Collectors.toList());

    }

    public EmployeeDTO createNewEmployee(EmployeeDTO inputEmployee){
        EmployeeEntity tosaventity=modelMapper.map(inputEmployee,EmployeeEntity.class);
        EmployeeEntity savedemployee=employeeRepository.save(tosaventity);
        return modelMapper.map(savedemployee, EmployeeDTO.class);
    }
    public EmployeeDTO updateEmployeeById(Long employeeId,EmployeeDTO employeeDTO){
        if(!isExistsEmployeeId(employeeId)) throw new ResourceNotFoundException("Employee not found with id "+employeeId);
        EmployeeEntity employeeEntity=modelMapper.map(employeeDTO,EmployeeEntity.class);
        employeeEntity.setId(employeeId);
        EmployeeEntity savedEmp=employeeRepository.save(employeeEntity);
        return modelMapper.map(savedEmp,EmployeeDTO.class);
    }

    public boolean isExistsEmployeeId(Long employeeId){
        return employeeRepository.existsById(employeeId);
    }
    public boolean deleteEmployeeById(Long employeeId){
        boolean exists=isExistsEmployeeId(employeeId);

        if(!exists) throw new ResourceNotFoundException("Employee not found with id "+employeeId);
       employeeRepository.deleteById(employeeId);
       return true;
    }

    public EmployeeDTO updatePartialEmployeeById(Long employeeId, Map<String,Object> updates){
        boolean exists=isExistsEmployeeId(employeeId);
        if(!exists)  throw new ResourceNotFoundException("Employee not found with id "+employeeId);;
  //we are only updating the fields that are present in updates , not all fields so we will use reflection
//        where we can directly go to an object and we can update the field of that object direclty
       EmployeeEntity employeeEntity=employeeRepository.findById(employeeId).get();
        updates.forEach((key,value)->{
           Field fieldToUpdated= ReflectionUtils.findField(EmployeeEntity.class,key);
           fieldToUpdated.setAccessible(true);
           ReflectionUtils.setField(fieldToUpdated,employeeEntity,value);
        });

        return modelMapper.map(employeeRepository.save(employeeEntity),EmployeeDTO.class);
    }
}
