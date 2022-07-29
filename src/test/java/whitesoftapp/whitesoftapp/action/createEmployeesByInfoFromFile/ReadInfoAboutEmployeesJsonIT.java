package whitesoftapp.whitesoftapp.action.createEmployeesByInfoFromFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import whitesoftapp.controller.utils.mapper.employee.EmployeeMapper;
import whitesoftapp.model.Employee;
import whitesoftapp.repository.EmployeeRepository;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class ReadInfoAboutEmployeesJsonIT {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    EmployeeMapper employeeMapper;

    @Test
    void readEmployeesFromFile() throws IOException {
        //Arrange
        List<Employee> listEmployees = objectMapper.readValue(new File("src/main/resources/Employee.json"), new TypeReference<List<Employee>>() {});

        //Act
        employeeRepository.saveAll(listEmployees);

        //Assert
        assertThat(employeeRepository.findAll()).isEqualTo(listEmployees);
    }


}