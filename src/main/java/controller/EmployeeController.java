package controller;

import model.dtos.CreateEmployeeDto;
import lombok.RequiredArgsConstructor;
import model.dtos.DtoEntity;
import model.dtos.UpdateEmployeeDto;
import service.EmployeeService;

import java.util.UUID;

@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeServise;

    public DtoEntity create(CreateEmployeeDto createEmployeeDto) {
        return employeeServise.createEmployee(createEmployeeDto);
    }

    public void update(UUID id, UpdateEmployeeDto updateEmployeeDto){
        employeeServise.updateEmployee(id,updateEmployeeDto);
    }

    public DtoEntity getById(UUID id){
        return employeeServise.getById(id);
    }


}







