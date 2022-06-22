package whitesoftapp.whitesoftapp.action;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import whitesoftapp.whitesoftapp.arguments.CreateEmployeeArgument;
import whitesoftapp.whitesoftapp.model.dtos.employee.CreateEmployeeDto;
import whitesoftapp.whitesoftapp.service.employee.EmployeeService;

@RequiredArgsConstructor
@Component
public class CreateEmployeeArgumentAction {

    private final EmployeeService employeeService;

    public CreateEmployeeArgument create(CreateEmployeeDto createEmployeeDto) {
        return employeeService.convertToCreateEmployeeArgument(createEmployeeDto);
    }
}
