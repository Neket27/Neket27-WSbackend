package service;

import lombok.NoArgsConstructor;
import model.Employee;
import model.dtos.CreateEmployeeDto;
import model.dtos.DtoEntity;
import model.dtos.UpdateEmployeeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.DtoUtils;

import java.util.UUID;

@NoArgsConstructor
@Service
public class EmployeeService {

    @Autowired
    private EmployeeCardService inMemoryEmployeeCard;//не видит бин(inMemoryEmployeeCar) через конструктор

    public DtoEntity createEmployee(DtoEntity employeeCreateDto) {
        Employee employee = (Employee) new DtoUtils().convertToEntity(new Employee(), employeeCreateDto);
        inMemoryEmployeeCard.add(employee);
        System.out.println("Service_Employee= " + new DtoUtils().convertToDto(employee, new CreateEmployeeDto()));
        DtoEntity employeeDto = new DtoUtils().convertToDto(employee, new CreateEmployeeDto());
        return employeeDto;
    }

    public void updateEmployee(UUID id,DtoEntity updateEmployeeDto) {
        Employee updateEmployee =(Employee) new DtoUtils().convertToEntity(new Employee(),updateEmployeeDto);
        inMemoryEmployeeCard.set(id,updateEmployee);
    }

    public DtoEntity getById(UUID id){
       return (DtoEntity) inMemoryEmployeeCard.get(id);
    }
}
