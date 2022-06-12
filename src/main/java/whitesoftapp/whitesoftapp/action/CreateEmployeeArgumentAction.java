package whitesoftapp.whitesoftapp.action;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import whitesoftapp.whitesoftapp.argument.CreateEmployeeArgument;
import whitesoftapp.whitesoftapp.model.dtos.employee.CreateEmployeeDto;
import whitesoftapp.whitesoftapp.service.EmployeeService;

@RequiredArgsConstructor
@Component
public class CreateEmployeeArgumentAction {

    private final EmployeeService employeeService;

    public CreateEmployeeArgument create(CreateEmployeeDto createEmployeeDto) {
        return employeeService.convertToCreateEmployeeArgument(createEmployeeDto);
    }
}
