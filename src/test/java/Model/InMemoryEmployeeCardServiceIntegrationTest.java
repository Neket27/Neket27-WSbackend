package Model;

import EmployeeCard.Employee;
import EmployeeCard.Post;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

public class InMemoryEmployeeCardServiceIntegrationTest {
    @Test
    void getInId() {
        Employee employee = new Employee();
        Post post =new Post(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"),"name");
        employee.setPost(post);
        InMemoryEmployeeCardService employeeCardService =new InMemoryEmployeeCardService(employee);
        Employee employeeResult=employeeCardService.get(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"));
        assertThat(employeeResult).isEqualTo(employee);
    }

    @Test
    void getInFirstNameAndLastName(){
        Employee employee =new Employee();
        employee.setFirstName("FirstName");
        employee.setLastName("LastName");
        InMemoryEmployeeCardService employeeCardService =new InMemoryEmployeeCardService(employee);
        Employee employeeResult = employeeCardService.get("FirstName","LastName");
        assertThat(employeeResult).isEqualTo(employee);

    }


}
