package model;

import employeeCard.Employee;
import employeeCard.Post;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.UUID;

public class InMemoryEmployeeCardServiceIntegrationTest {

    Employee employee = new Employee();

    @Test
    void getInId() {
        //Arrange
        Post post = new Post(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"), "name");
        InMemoryEmployeeCardService employeeCardService = new InMemoryEmployeeCardService(employee);

        //Act
        employee.setPost(post);
        Employee employeeResult = employeeCardService.get(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"));

        //Assert
        assertThat(employeeResult).isEqualTo(employee);
    }

    @Test
    void getInFirstNameAndLastName() {
        //Arrange
        InMemoryEmployeeCardService employeeCardService = new InMemoryEmployeeCardService(employee);

        //Act
        employee.setFirstName("FirstName");
        employee.setLastName("LastName");
        Employee employeeResult = employeeCardService.get("FirstName", "LastName");

        //Assert
        assertThat(employeeResult).isEqualTo(employee);

    }

}
