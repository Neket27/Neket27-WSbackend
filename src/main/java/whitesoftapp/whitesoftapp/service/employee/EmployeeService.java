package whitesoftapp.whitesoftapp.service.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import whitesoftapp.whitesoftapp.arguments.CreateEmployeeArgument;
import whitesoftapp.whitesoftapp.arguments.UpdateEmployeeArgument;
import whitesoftapp.whitesoftapp.controller.utils.mapper.employee.EmployeeMapper;
import whitesoftapp.whitesoftapp.exception.ApiRequestExceptionEmployee;
import whitesoftapp.whitesoftapp.model.Employee;
import whitesoftapp.whitesoftapp.model.dtos.employee.CreateEmployeeDto;
import whitesoftapp.whitesoftapp.model.dtos.employee.EmployeeDto;
import whitesoftapp.whitesoftapp.model.dtos.employee.UpdateEmployeeDto;
import whitesoftapp.whitesoftapp.repository.InMemoryEmployeeCard;

import java.util.HashMap;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class EmployeeService {

    private final InMemoryEmployeeCard inMemoryEmployeeCard;
    private final EmployeeMapper employeeMapper;


    public EmployeeDto createEmployee(CreateEmployeeArgument createEmployeeArgument) {
        Employee employee = employeeMapper.toEntityFromUpdateArgument(createEmployeeArgument);
        System.out.println("EMP="+employee);
        inMemoryEmployeeCard.add(employee);
        return employeeMapper.toDto(employee);
    }

    public void updateEmployee(UUID id, UpdateEmployeeArgument updateEmployeeArgument) {
        Employee employee = employeeMapper.toEntityFromUpdateArgument(updateEmployeeArgument);
        inMemoryEmployeeCard.put(id, employee);
    }

      public EmployeeDto getById(UUID id) {
        Employee employee = inMemoryEmployeeCard.get(id);
        if (employee == null)
            throw new ApiRequestExceptionEmployee("Нет Работника с таким id");

        return employeeMapper.toDto(employee);
    }

    public HashMap<UUID, EmployeeDto> getList() {
        return employeeMapper.toListDto(inMemoryEmployeeCard.getList());
    }

    public void remove(UUID id) {
        inMemoryEmployeeCard.remove(id);
    }


    public CreateEmployeeArgument convertToCreateEmployeeArgument(CreateEmployeeDto createEmployeeDto) {
        return employeeMapper.toArgumentFromUpdateEmployeeDto(createEmployeeDto);
    }

    public UpdateEmployeeArgument convertToUpdateEmployeeArgument(UpdateEmployeeDto updateEmployeeDto) {
        return employeeMapper.toArgumentFromUpdateEmployeeDto(updateEmployeeDto);
    }

}
