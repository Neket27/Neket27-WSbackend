package controller;

import model.dtos.CreateEmployeeDto;
import lombok.RequiredArgsConstructor;
import model.dtos.DtoEntity;
import service.EmployeeService;

@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeServise;

    public DtoEntity create(CreateEmployeeDto createEmployeeDto) {
        return employeeServise.createEmployee(createEmployeeDto);

    }


}







