package cardemployee.wsapp.model;

import model.Employee;
import model.Post;
import repository.InMemoryEmployeeCard;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.UUID;

@SpringBootTest
public class InMemoryEmployeeCardServiceIntegrationTest {

    @Autowired
    private Employee employee;
    @Autowired
    private InMemoryEmployeeCard employeeCardService;

    @Test
    void getInId() {
        //Arrange
        Post post = new Post(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"), "name");
        employee.setPost(post);
        employeeCardService.add(employee);

        //Act
        Employee employeeResult = employeeCardService.get(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"));

        //Assert
        assertThat(employeeResult).isEqualTo(employee);
    }

    @Test
    void getInFirstNameAndLastName() {
        //Arrange
        employee.setFirstName("FirstName");
        employee.setLastName("LastName");

        //Act
        employeeCardService.add(employee);
        Employee employeeResult = employeeCardService.get("FirstName", "LastName");

        //Assert
        assertThat(employeeResult).isEqualTo(employee);

    }

}
