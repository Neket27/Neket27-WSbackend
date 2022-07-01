package whitesoftapp.action;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import whitesoftapp.arguments.CreateEmployeeArgument;
import whitesoftapp.controller.utils.mapper.employee.EmployeeMapper;
import whitesoftapp.model.dtos.employee.CreateEmployeeDto;
import whitesoftapp.service.employee.EmployeeService;

import javax.validation.Valid;

@RequiredArgsConstructor
@Component
public class CreateEmployeeArgumentAction {

    private final EmployeeMapper employeeMapper;

    public CreateEmployeeArgument create(@Valid CreateEmployeeDto createEmployeeDto) {
        return employeeMapper.toArgumentFromCreateEmployeeDto(createEmployeeDto);
    }
}
