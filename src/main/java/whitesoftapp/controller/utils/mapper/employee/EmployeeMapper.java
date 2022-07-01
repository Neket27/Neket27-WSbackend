package whitesoftapp.controller.utils.mapper.employee;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import whitesoftapp.arguments.CreateEmployeeArgument;
import whitesoftapp.arguments.UpdateEmployeeArgument;
import whitesoftapp.model.Employee;
import whitesoftapp.model.dtos.employee.CreateEmployeeDto;
import whitesoftapp.model.dtos.employee.EmployeeDto;
import whitesoftapp.model.dtos.employee.UpdateEmployeeDto;

import java.util.HashMap;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {


    EmployeeDto toDto(Employee employee);

    Employee toEntity(EmployeeDto employeeDto);

    Employee toEntityFromUpdateArgument(CreateEmployeeArgument createEmployeeArgument);

    Employee toEntityFromUpdateArgument(UpdateEmployeeArgument updateEmployeeArgument);

    CreateEmployeeArgument toArgumentFromCreateEmployeeDto(CreateEmployeeDto createEmployeeDto);

    UpdateEmployeeArgument toArgumentFromUpdateEmployeeDto(UpdateEmployeeDto updateEmployeeDto);

    HashMap<UUID, EmployeeDto> toListDto(HashMap<UUID,Employee> hashMap);

}

