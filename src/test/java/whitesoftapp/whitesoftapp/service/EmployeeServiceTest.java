package whitesoftapp.whitesoftapp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import whitesoftapp.whitesoftapp.action.CreateEmployeeArgumentAction;
import whitesoftapp.whitesoftapp.argument.CreateEmployeeArgument;
import whitesoftapp.whitesoftapp.argument.UpdateEmployeeArgument;
import whitesoftapp.whitesoftapp.model.Contacts;
import whitesoftapp.whitesoftapp.model.Employee;
import whitesoftapp.whitesoftapp.model.JobType;
import whitesoftapp.whitesoftapp.model.Post;
import whitesoftapp.whitesoftapp.model.dtos.employee.CreateEmployeeDto;
import whitesoftapp.whitesoftapp.model.dtos.employee.EmployeeDto;
import whitesoftapp.whitesoftapp.repository.InMemoryEmployeeCard;

import java.util.Collections;
import java.util.HashMap;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private InMemoryEmployeeCard inMemoryEmployeeCard;
    @Autowired
    private CreateEmployeeArgumentAction createEmployeeArgumentAction;

    Post post = new Post(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"), "newPost");

    Employee employee = Employee.builder()
            .id(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"))
            .firstName("firstName")
            .lastName("LastName")
            .description("descriptions")
            .characteristics(Collections.singletonList("characteristics"))
            .post(post)
            .contacts(new Contacts())
            .jobType(new JobType("1", "2", "3", "4", "5"))
            .build();

    EmployeeDto employeeDto = EmployeeDto.builder()
            .id(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"))
            .firstName("firstName")
            .lastName("LastName")
            .description("descriptions")
            .characteristics(Collections.singletonList("characteristics"))
            .post(post)
            .contacts(new Contacts())
            .jobType(new JobType("1", "2", "3", "4", "5"))
            .build();

    CreateEmployeeDto createEmployeeDto = CreateEmployeeDto.builder()
            .firstName("firstName")
            .lastName("LastName")
            .description("descriptions")
            .characteristics(Collections.singletonList("characteristics"))
            .post(post)
            .contacts(new Contacts())
            .jobType(new JobType("1", "2", "3", "4", "5"))
            .build();

    CreateEmployeeArgument createEmployeeArgument = CreateEmployeeArgument.builder()
            .firstName(employee.getFirstName())
            .lastName(employee.getLastName())
            .description(employee.getDescription())
            .characteristics(employee.getCharacteristics())
            .post(employee.getPost())
            .contacts(employee.getContacts())
            .jobType(employee.getJobType())
            .build();

    UpdateEmployeeArgument updateEmployee = UpdateEmployeeArgument.builder()
            .id(employee.getId())
            .firstName(employee.getFirstName())
            .lastName(employee.getLastName())
            .description(employee.getDescription())
            .characteristics(employee.getCharacteristics())
            .post(employee.getPost())
            .contacts(employee.getContacts())
            .jobType(employee.getJobType())
            .build();

    @Test
    void createEmployee() {
        //Act
        employeeService.createEmployee(employeeDto);
        Employee employeeResult =inMemoryEmployeeCard.get(employee.getId());
        //Assert
        assertThat(employeeResult).isEqualTo(employee);

    }

    @Test
    void CreateEmployeeArgument() {
        //Arrange
        CreateEmployeeArgument createEmployeeArgument = CreateEmployeeArgument.builder()
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .description(employee.getDescription())
                .characteristics(employee.getCharacteristics())
                .post(employee.getPost())
                .contacts(employee.getContacts())
                .jobType(employee.getJobType())
                .build();

        //Act
        employeeService.createEmployee(createEmployeeArgument);
        Employee employeeResult=inMemoryEmployeeCard.get(employee.getId());

        //Assert
        assertThat(employeeResult).isEqualTo(employee);
    }

    @Test
    void updateEmployee() {

        //Act
        employeeService.updateEmployee(employee.getId(), employeeDto);
        Employee employeeResult=inMemoryEmployeeCard.get(employee.getId());
        //Assert
        assertThat(employeeResult).isEqualTo(employee);
    }

    @Test
    void UpdateEmployeeArgument() {
        //Arrange

        //Act
        employeeService.updateEmployee(updateEmployee.getId(), updateEmployee);
        Employee employeeResult=inMemoryEmployeeCard.get(updateEmployee.getId());
        //Assert
        assertThat(employeeResult).isEqualTo(employee);
    }

    @Test
    void getById() {
        //Arrange
        inMemoryEmployeeCard.put(employee.getId(), employee);

        //Act
        EmployeeDto employee = employeeService.getById(employeeDto.getId());

        //Assert
        assertThat(employeeDto).isEqualTo(employee);
    }

    @Test
    void getList() {
        //Arrange
        inMemoryEmployeeCard.getList().clear();
        inMemoryEmployeeCard.put(employee.getId(), employee);
        HashMap storage = new HashMap();
        storage.put(employeeDto.getId(), employeeDto);

        //Act
        HashMap<UUID, EmployeeDto> employeesResult = employeeService.getList();

        //Assert
        assertThat(employeesResult).isEqualTo(storage);
    }

    @Test
    void remove() {
        //Arrange
        inMemoryEmployeeCard.put(employee.getId(), employee);

        //Act
        employeeService.remove(employee.getId());
        Employee employeeResult= inMemoryEmployeeCard.get(employee.getId());

        //Assert
        assertThat(employeeResult).isEqualTo(null);
    }

    @Test
    void convertToCreateEmployeeArgument() {
        //Act
        CreateEmployeeArgument argument = employeeService.convertToCreateEmployeeArgument(createEmployeeDto);

        //Assert
        assertThat(argument).isEqualTo(createEmployeeArgument).isEqualTo(argument);
    }
}