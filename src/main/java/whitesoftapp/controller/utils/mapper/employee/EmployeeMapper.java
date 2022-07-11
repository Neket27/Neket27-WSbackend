package whitesoftapp.controller.utils.mapper.employee;

import org.mapstruct.Mapper;
import whitesoftapp.arguments.CreateEmployeeArgument;
import whitesoftapp.arguments.UpdateEmployeeArgument;
import whitesoftapp.model.Employee;
import whitesoftapp.model.dtos.employee.CreateEmployeeDto;
import whitesoftapp.model.dtos.employee.EmployeeDto;
import whitesoftapp.model.dtos.employee.UpdateEmployeeDto;

import java.util.Map;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeDto toDto(Employee employee);

    Employee toEntity(EmployeeDto employeeDto);

    Employee toEntityFromUpdateArgument(CreateEmployeeArgument createEmployeeArgument);

    Employee toEntityFromUpdateArgument(UpdateEmployeeArgument updateEmployeeArgument);

    CreateEmployeeArgument toArgumentFromCreateEmployeeDto(CreateEmployeeDto createEmployeeDto);

    UpdateEmployeeArgument toArgumentFromUpdateEmployeeDto(UpdateEmployeeDto updateEmployeeDto);

    Map<UUID, EmployeeDto> toListDto(Map<UUID,Employee> hashMap);

}

