package whitesoftapp.whitesoftapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import whitesoftapp.whitesoftapp.argument.CreateEmployeeArgument;
import whitesoftapp.whitesoftapp.argument.UpdateEmployeeArgument;
import whitesoftapp.whitesoftapp.exception.ApiRequestExceptionEmployee;
import whitesoftapp.whitesoftapp.model.Employee;
import whitesoftapp.whitesoftapp.model.dtos.employee.CreateEmployeeDto;
import whitesoftapp.whitesoftapp.model.dtos.employee.EmployeeDto;
import whitesoftapp.whitesoftapp.model.dtos.employee.UpdateEmployeeDto;
import whitesoftapp.whitesoftapp.repository.InMemoryContacts;
import whitesoftapp.whitesoftapp.repository.InMemoryEmployeeCard;
import whitesoftapp.whitesoftapp.repository.InMemoryPost;
import whitesoftapp.whitesoftapp.utils.mapper.EmployeeMapper;
import whitesoftapp.whitesoftapp.utils.mapper.EmployeeMapperBuilder;

import java.util.HashMap;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class EmployeeService {

    private final InMemoryEmployeeCard inMemoryEmployeeCard;
    private final EmployeeMapper employeeMapper;
    private final EmployeeMapperBuilder employeeMapperBuilder;


    public EmployeeDto createEmployee(CreateEmployeeArgument createEmployeeArgument) {
        Employee employee = employeeMapperBuilder.toEntity(createEmployeeArgument);
        inMemoryEmployeeCard.add(employee);
        return employeeMapperBuilder.toDto(employee);
    }

    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = employeeMapperBuilder.toEntity(employeeDto);
        inMemoryEmployeeCard.add(employee);
        return employeeMapperBuilder.toDto(employee);
    }

    public void updateEmployee(UUID id, UpdateEmployeeArgument updateEmployeeArgument) {
        Employee employee = employeeMapperBuilder.toEntity(updateEmployeeArgument);
        inMemoryEmployeeCard.put(id, employee);
    }

    public void updateEmployee(UUID id, EmployeeDto employeeDto) {
        Employee employee = employeeMapperBuilder.toEntity(employeeDto);
        inMemoryEmployeeCard.put(id, employee);
    }

    public EmployeeDto getById(UUID id) {
        Employee employee = inMemoryEmployeeCard.get(id);
        if (employee == null)
            throw new ApiRequestExceptionEmployee("Нет Работника с таким id");

        return employeeMapperBuilder.toDto(employee);
    }

    public HashMap<UUID, EmployeeDto> getList() {
        return employeeMapper.toListEmployeeDto(inMemoryEmployeeCard.getList());
    }

    public void remove(UUID id) {
        inMemoryEmployeeCard.remove(id);
    }


    public CreateEmployeeArgument convertToCreateEmployeeArgument(CreateEmployeeDto createEmployeeDto) {
        return employeeMapper.toArgument(createEmployeeDto);
    }

    public UpdateEmployeeArgument convertToUpdateEmployeeArgument(UpdateEmployeeDto updateEmployeeDto) {
        return employeeMapper.toArgument(updateEmployeeDto);
    }

}
