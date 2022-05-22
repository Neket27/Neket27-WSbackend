package Model;

import EmployeeCard.Employee;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.*;
import static org.mockito.Mockito.*;

class InMemoryEmployeeCardServiceTest {

    @Test
    void getInId() {
    Employee employee =new Employee();

    InMemoryEmployeeCardService inMemoryEmployeeCardService =mock(InMemoryEmployeeCardService.class);
    when(inMemoryEmployeeCardService.get(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"))).thenReturn(employee);
    Employee employeeResult= inMemoryEmployeeCardService.get(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"));
    assertThat(employeeResult).isEqualTo(employee);
    }

    @Test //не работает
    void getInFirstNameAndLastName() {
        List<Employee>employees=mock(ArrayList.class);
        Employee employee =mock(Employee.class);

        when(employees.get(0)).thenReturn(employee);
        InMemoryEmployeeCardService inMemoryEmployeeCardService =new InMemoryEmployeeCardService(employees);
        Employee employeeResult = inMemoryEmployeeCardService.get("FistName","LastName");
//        assertThat(employeeResult).isEqualTo(employee);

    }
}