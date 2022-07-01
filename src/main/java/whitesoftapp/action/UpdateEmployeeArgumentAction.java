package whitesoftapp.action;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import whitesoftapp.arguments.UpdateEmployeeArgument;
import whitesoftapp.controller.utils.mapper.employee.EmployeeMapper;
import whitesoftapp.model.dtos.employee.UpdateEmployeeDto;
import whitesoftapp.service.employee.EmployeeService;

@RequiredArgsConstructor
@Component
public class UpdateEmployeeArgumentAction {

    private final EmployeeMapper employeeMapper;

    public UpdateEmployeeArgument update(UpdateEmployeeDto updateEmployeeDto) {
        return employeeMapper.toArgumentFromUpdateEmployeeDto(updateEmployeeDto);
    }
}
