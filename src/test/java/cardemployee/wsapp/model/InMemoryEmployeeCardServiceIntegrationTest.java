package cardemployee.wsapp.model;

import controller.EmployeeController;
import model.Employee;
import model.Post;
import model.dtos.CreateEmployeeDto;
import model.dtos.DtoEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import service.EmployeeCardService;
import service.EmployeeService;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.UUID;

@SpringBootTest
public class InMemoryEmployeeCardServiceIntegrationTest {

    @Autowired
    private Employee employee;
    @Autowired
    private EmployeeCardService employeeCardService;

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

    @Test
    void setInId() {
        //Arrange
        Post post = new Post(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"), "name");
        employee.setPost(post);

        //Act
        employeeCardService.add(employee);
        Employee employeeUpdate = Employee.builder()
                .firstName("1")
                .lastName("2")
                .description("3")
                .characteristics(new ArrayList<>())
                .post(post)
                .build();
        employeeCardService.set(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"), employeeUpdate);

        //Assert
        assertThat(employeeUpdate).isEqualTo(employeeCardService.get(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707")));
    }

    @Test
    void getById(){
        //Arrange
        Post post = new Post(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"), "name");
        EmployeeService employeeService=new EmployeeService();
        CreateEmployeeDto createEmployeeDto= CreateEmployeeDto.builder()
                .firstName("1")
                .lastName("2")
                .description("3")
                .characteristics(new ArrayList<>())
                .post(post)
                .build();
        employeeService.createEmployee(createEmployeeDto);
        EmployeeController employeeController=new EmployeeController(employeeService);

        //Act
        DtoEntity employeeResult = employeeController.getById(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"));

        //Assert
//        assertThat((Employee)employeeResult).isEqualTo(employee);
    }

}
