package whitesoftapp.whitesoftapp.action.createEmployeesByInfoFromFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import whitesoftapp.whitesoftapp.controller.utils.mapper.employee.EmployeeMapper;
import whitesoftapp.whitesoftapp.model.Employee;
import whitesoftapp.whitesoftapp.repository.InMemoryEmployeeCard;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class ReadInfoAboutEmployeesJsonIT {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    InMemoryEmployeeCard inMemoryEmployeeCard;
    @Autowired
    EmployeeMapper employeeMapper;

    @Test
    void readEmployeesFromFile() throws IOException {
        //Arrange
        List<Employee> listEmployees = objectMapper.readValue(new File("src/main/resources/Employees.json"), new TypeReference<List<Employee>>() {});

        HashMap storage = new HashMap();
        listEmployees.forEach(e->storage.put(e.getId(),e));
        //Act
        listEmployees.forEach(employee ->inMemoryEmployeeCard.add(employee));

        //Assert
        assertThat(inMemoryEmployeeCard.getList()).isEqualTo(storage);
    }


}