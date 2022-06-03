package whitesoftapp.whitesoftapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import whitesoftapp.whitesoftapp.model.Employee;
import whitesoftapp.whitesoftapp.model.dtos.CreateEmployeeDto;
import whitesoftapp.whitesoftapp.model.dtos.DtoEntity;
import whitesoftapp.whitesoftapp.model.dtos.GetByIdEmployeeDto;
import whitesoftapp.whitesoftapp.utils.DtoUtils;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class EmployeeService {

    private final EmployeeCardService inMemoryEmployeeCard;//не видит бин(inMemoryEmployeeCard) через конструктор

    public DtoEntity createEmployee(DtoEntity employeeCreateDto) {
        Employee employee = (Employee) new DtoUtils().convertToEntity(new Employee(), employeeCreateDto);
        inMemoryEmployeeCard.add(employee);
//        System.out.println("Service_Employee= " + new DtoUtils().convertToDto(employee, new CreateEmployeeDto()));
        DtoEntity employeeDto = new DtoUtils().convertToDto(employee, new CreateEmployeeDto());
        return employeeDto;
    }

    public void updateEmployee(UUID id,DtoEntity updateEmployeeDto) {
        Employee updateEmployee =(Employee) new DtoUtils().convertToEntity(new Employee(),updateEmployeeDto);
        inMemoryEmployeeCard.set(id,updateEmployee);
    }

    public DtoEntity getById(UUID id){
        Employee employee=inMemoryEmployeeCard.get(id);
        DtoEntity employeeDto = new DtoUtils().convertToDto(employee, new GetByIdEmployeeDto());
       return employeeDto;
    }
}
