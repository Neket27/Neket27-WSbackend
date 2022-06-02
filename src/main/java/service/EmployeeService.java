package service;

import model.Employee;
import model.dtos.CreateEmployeeDto;
import model.dtos.DtoEntity;
import org.springframework.stereotype.Service;
import utils.DtoUtils;

@Service
public class EmployeeService {

    public DtoEntity createEmployee(DtoEntity employeeCreateDto){
        Employee employee=(Employee) new DtoUtils().convertToEntity(new Employee(),employeeCreateDto);
        return new DtoUtils().convertToDto(employee,new CreateEmployeeDto());
    }
}
