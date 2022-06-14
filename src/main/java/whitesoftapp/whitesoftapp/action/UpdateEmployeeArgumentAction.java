package whitesoftapp.whitesoftapp.action;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import whitesoftapp.whitesoftapp.argument.UpdateEmployeeArgument;
import whitesoftapp.whitesoftapp.model.dtos.employee.UpdateEmployeeDto;
import whitesoftapp.whitesoftapp.service.EmployeeService;

@RequiredArgsConstructor
@Component
public class UpdateEmployeeArgumentAction {

    private final EmployeeService employeeService;

    public UpdateEmployeeArgument update(UpdateEmployeeDto updateEmployeeDto) {
        return employeeService.convertToUpdateEmployeeArgument(updateEmployeeDto);
    }
}
