package model;

import employeeCard.Employee;
import employeeCard.Post;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.UUID;

public class InMemoryEmployeeCardServiceIntegrationTest {

    Employee employee = new Employee();
    InMemoryEmployeeCardService employeeCardService = new InMemoryEmployeeCardService();
    @Test
    void getInId() {
        //Arrange
        Post post = new Post(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"), "name");

        //Act
        employee.setPost(post);
        employeeCardService.add(employee);
        Employee employeeResult = employeeCardService.get(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"));

        //Assert
        assertThat(employeeResult).isEqualTo(employee);
    }

    @Test
    void getInFirstNameAndLastName() {
        //Arrange

        //Act
        employee.setFirstName("FirstName");
        employee.setLastName("LastName");
        employeeCardService.add(employee);
        Employee employeeResult = employeeCardService.get("FirstName", "LastName");

        //Assert
        assertThat(employeeResult).isEqualTo(employee);

    }

}
