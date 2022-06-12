package whitesoftapp.whitesoftapp.utils.mapper;

import org.mapstruct.Mapper;
import whitesoftapp.whitesoftapp.argument.CreateEmployeeArgument;
import whitesoftapp.whitesoftapp.model.Employee;
import whitesoftapp.whitesoftapp.model.dtos.employee.CreateEmployeeDto;
import whitesoftapp.whitesoftapp.model.dtos.employee.EmployeeDto;

import java.util.HashMap;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeDto toDto(Employee employee);

    Employee toEmployee(EmployeeDto employeeDto);

    Employee toEmployeeFromEmployeeDto(EmployeeDto employeeDto);

    Employee toEmployeeFromArgument(CreateEmployeeArgument createEmployeeArgument);

    CreateEmployeeArgument toArgument(CreateEmployeeDto createEmployeeDto);

    HashMap<UUID, EmployeeDto> toListEmployeeDto(HashMap<UUID,Employee> hashMap);

}
