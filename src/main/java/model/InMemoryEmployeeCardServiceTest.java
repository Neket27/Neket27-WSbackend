package model;

import employeeCard.Employee;
import org.junit.jupiter.api.Test;
import service.EmployeeCardService;
import java.util.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class InMemoryEmployeeCardServiceTest {

    EmployeeCardService employeeCardService = mock(EmployeeCardService.class);
    Employee employee = mock(Employee.class);

    @Test
    void getInId() {
        //Arrange

        //Act
        when(employeeCardService.get(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"))).thenReturn(employee);
        Employee result = employeeCardService.get(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"));
        //Assert
        assertThat(result).isEqualTo(employee);
    }

    @Test
    void getInFirstNameAndLastName() {
        //Arrange

        //Act
        when(employeeCardService.get(eq("FistName"), eq("LastName"))).thenReturn(employee);
        Employee result = employeeCardService.get("FistName", "LastName");
        //Assert
        assertThat(result).isEqualTo(employee);
    }
}