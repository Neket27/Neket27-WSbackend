package whitesoftapp.whitesoftapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import whitesoftapp.whitesoftapp.model.dtos.CreateEmployeeDto;
import whitesoftapp.whitesoftapp.model.dtos.DtoEntity;
import whitesoftapp.whitesoftapp.model.dtos.UpdateEmployeeDto;
import whitesoftapp.whitesoftapp.service.EmployeeService;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class EmployeeController {

    private final EmployeeService employeeServise;

    public DtoEntity create(CreateEmployeeDto createEmployeeDto) {
        return employeeServise.createEmployee(createEmployeeDto);
    }

    public void update(UUID id, UpdateEmployeeDto updateEmployeeDto) {
        employeeServise.updateEmployee(id, updateEmployeeDto);
    }

    public DtoEntity getById(UUID id) {
        return employeeServise.getById(id);
    }

}







