package whitesoftapp.whitesoftapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import whitesoftapp.whitesoftapp.model.Employee;
import whitesoftapp.whitesoftapp.model.dtos.*;
import whitesoftapp.whitesoftapp.notFoundException.EmployeeNotFoundException;
import whitesoftapp.whitesoftapp.repository.InMemoryEmployeeCard;
import whitesoftapp.whitesoftapp.utils.DtoUtilsEmployee;
import java.util.*;

@RequiredArgsConstructor
@Service
public class EmployeeService {

    private final DtoUtilsEmployee dtoUtils;
    private final InMemoryEmployeeCard inMemoryEmployeeCard;

    public EmployeeDto createEmployee(UUID id,EmployeeDto employeeCreateDto) {
        Employee employee = dtoUtils.convertToEntity(new Employee(), employeeCreateDto);
        inMemoryEmployeeCard.put(id,employee);
        return dtoUtils.convertToDto(employee, new EmployeeDto());
    }

    public void updateEmployee(UUID id, EmployeeDto updateEmployeeDto) {
        Employee updateEmployee = dtoUtils.convertToEntity(new Employee(), updateEmployeeDto);
        inMemoryEmployeeCard.put(id, updateEmployee);
    }

    public EmployeeDto getById(UUID id)  {
        Employee employee = inMemoryEmployeeCard.get(id);
        if(employee==null){
            throw new EmployeeNotFoundException(id);
        }
        return dtoUtils.convertToDto(employee, new EmployeeDto());
    }

    public HashMap<UUID,EmployeeDto> getList() {
        return dtoUtils.convertListToDto(inMemoryEmployeeCard.getList(), EmployeeDto.class);
    }

    public void remove(UUID id) {
        inMemoryEmployeeCard.remove(id);
    }

}
