package model;

import employeeCard.Employee;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.*;
import static org.mockito.Mockito.*;

class InMemoryEmployeeCardServiceTest {

    Employee employee = new Employee();

    @Test
    void getInId() {
        //Arrange
        InMemoryEmployeeCardService inMemoryEmployeeCardService = mock(InMemoryEmployeeCardService.class);

        //Act
        when(inMemoryEmployeeCardService.get(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"))).thenReturn(employee);
        Employee employeeResult = inMemoryEmployeeCardService.get(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"));

        //Assert
        assertThat(employeeResult).isEqualTo(employee);
    }

    @Test
    void getInFirstNameAndLastName() {
        //Arrange
        InMemoryEmployeeCardService inMemoryEmployeeCardService = mock(InMemoryEmployeeCardService.class);

        //Act
        when(inMemoryEmployeeCardService.get(eq("FistName"), eq("LastName"))).thenReturn(employee);
        Employee employeeResult = inMemoryEmployeeCardService.get("FistName", "LastName");

        //Assert
        assertThat(employeeResult).isEqualTo(employee);
    }
}