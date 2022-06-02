package controller;

import model.dtos.CreateEmployeeDto;
import lombok.RequiredArgsConstructor;
import model.dtos.DtoEntity;
import service.EmployeeService;

@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    public DtoEntity create(CreateEmployeeDto createEmployeeDto) {
        return employeeService.createEmployee(createEmployeeDto);

    }


}







