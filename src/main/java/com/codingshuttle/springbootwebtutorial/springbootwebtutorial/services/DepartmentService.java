package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.services;

import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.dto.DepartmentDTO;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.dto.EmployeeDTO;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.entities.DepartmentEntity;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.entities.EmployeeEntity;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.exceptions.ResourceNotFoundException;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.repositories.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentService {



    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;


    public DepartmentService(DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }



    public DepartmentDTO createNewDepartment(DepartmentDTO inptDepartment){
        DepartmentEntity tosaveentity=modelMapper.map(inptDepartment, DepartmentEntity.class);
        DepartmentEntity saveddepartment=departmentRepository.save(tosaveentity);
        return modelMapper.map(saveddepartment, DepartmentDTO.class);
    }


    public boolean isExistsDepartmentId(Long departmentId){
        return departmentRepository.existsById(departmentId);
    }



    public List<DepartmentDTO> getAllDepartment(){
        List<DepartmentEntity> departmentEntities=departmentRepository.findAll();
        return departmentEntities.stream().map(departmentEntity -> modelMapper.map(departmentEntity, DepartmentDTO.class))
                .collect(Collectors.toList());
    }

    public Optional<DepartmentDTO> getDepartmentById(Long id){
//        EmployeeEntity employeeEntity=employeeRepository.findById(id).orElse(null);
//
//       return  modelMapper.map(employeeEntity, EmployeeDTO.class);

        return departmentRepository.findById(id).map(departmentEntity -> modelMapper.map(departmentEntity,DepartmentDTO.class));

    }



    public DepartmentDTO updateDepartmentById(DepartmentDTO departmentDTO,Long departmentId){
        if(!isExistsDepartmentId(departmentId)) throw  new ResourceNotFoundException("Department not found with id "+departmentId);

        DepartmentEntity departmentEntity=modelMapper.map(departmentDTO, DepartmentEntity.class);
        departmentEntity.setId(departmentId);

        DepartmentEntity savedDept=departmentRepository.save(departmentEntity);
        return modelMapper.map(savedDept, DepartmentDTO.class);
    }




    public boolean deleteDepartmentById(Long departmentId){
        boolean exists=isExistsDepartmentId(departmentId);
        if(!exists)  new ResourceNotFoundException("Department not found with id "+departmentId);
        departmentRepository.deleteById(departmentId);
        return true;
    }


}
