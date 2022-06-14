package whitesoftapp.whitesoftapp.utils.mapper;

import org.mapstruct.Mapper;
import whitesoftapp.whitesoftapp.argument.CreateEmployeeArgument;
import whitesoftapp.whitesoftapp.argument.UpdateEmployeeArgument;
import whitesoftapp.whitesoftapp.model.Employee;
import whitesoftapp.whitesoftapp.model.dtos.employee.CreateEmployeeDto;
import whitesoftapp.whitesoftapp.model.dtos.employee.EmployeeDto;
import whitesoftapp.whitesoftapp.model.dtos.employee.UpdateEmployeeDto;

import java.util.HashMap;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeDto toDto(Employee employee);

    Employee toEmployee(EmployeeDto employeeDto);

    Employee toEmployeeFromEmployeeDto(EmployeeDto employeeDto);

    Employee toEmployeeFromArgument(CreateEmployeeArgument createEmployeeArgument);

    Employee toEmployeeFromArgument(UpdateEmployeeArgument updateEmployeeArgument);

    CreateEmployeeArgument toArgument(CreateEmployeeDto createEmployeeDto);

    UpdateEmployeeArgument toArgument(UpdateEmployeeDto employeeDto);

    HashMap<UUID, EmployeeDto> toListEmployeeDto(HashMap<UUID,Employee> hashMap);

}
