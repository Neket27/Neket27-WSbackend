package whitesoftapp.whitesoftapp.controller.utils.mapper.employee;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import whitesoftapp.whitesoftapp.arguments.CreateEmployeeArgument;
import whitesoftapp.whitesoftapp.arguments.UpdateEmployeeArgument;
import whitesoftapp.whitesoftapp.model.Employee;
import whitesoftapp.whitesoftapp.model.dtos.employee.CreateEmployeeDto;
import whitesoftapp.whitesoftapp.model.dtos.employee.EmployeeDto;
import whitesoftapp.whitesoftapp.model.dtos.employee.UpdateEmployeeDto;

import java.util.HashMap;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mapping(source = "post", target = "postDto")
    @Mapping(source = "contacts", target = "contactsDto")
    EmployeeDto toDto(Employee employee);

    @Mapping(source = "postDto", target = "post")
    @Mapping(source = "contactsDto", target = "contacts")
    Employee toEntity(EmployeeDto employeeDto);

    @Mapping(source = "postDto", target = "post")
    @Mapping(source = "contactsDto", target = "contacts")
    Employee toEmployeeFromEmployeeDto(EmployeeDto employeeDto);

    @Mapping(source = "postDto", target = "post")
    @Mapping(source = "contactsDto", target = "contacts")
    Employee toEntityFromUpdateArgument(CreateEmployeeArgument createEmployeeArgument);

    @Mapping(source = "postDto", target = "post")
    @Mapping(source = "contactsDto", target = "contacts")
    Employee toEntityFromUpdateArgument(UpdateEmployeeArgument updateEmployeeArgument);

    CreateEmployeeArgument toArgumentFromUpdateEmployeeDto(CreateEmployeeDto createEmployeeDto);

    UpdateEmployeeArgument toArgumentFromUpdateEmployeeDto(UpdateEmployeeDto employeeDto);

    HashMap<UUID, EmployeeDto> toListDto(HashMap<UUID,Employee> hashMap);

}

