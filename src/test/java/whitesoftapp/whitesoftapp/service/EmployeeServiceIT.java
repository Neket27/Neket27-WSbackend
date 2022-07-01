package whitesoftapp.whitesoftapp.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import whitesoftapp.action.CreateEmployeeArgumentAction;
import whitesoftapp.arguments.CreateEmployeeArgument;
import whitesoftapp.arguments.UpdateEmployeeArgument;
import whitesoftapp.model.Contacts;
import whitesoftapp.model.Employee;
import whitesoftapp.model.JobType;
import whitesoftapp.model.Post;
import whitesoftapp.model.dtos.contacts.ContactsDto;
import whitesoftapp.model.dtos.employee.CreateEmployeeDto;
import whitesoftapp.model.dtos.employee.EmployeeDto;
import whitesoftapp.model.dtos.post.PostDto;
import whitesoftapp.repository.InMemoryEmployeeCard;
import whitesoftapp.service.employee.EmployeeService;


import java.util.Collections;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
class EmployeeServiceIT {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private InMemoryEmployeeCard inMemoryEmployeeCard;
    @Autowired
    private CreateEmployeeArgumentAction createEmployeeArgumentAction;

    Post post = new Post(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"), "newPost");
    PostDto postDto = new PostDto(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"), "newPost");


    Contacts contacts = Contacts.builder()
            .id(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"))
            .phone("89144033422")
            .email("nikitaivanovitc@gmail.com")
            .email("nikitaivanovitc@gmail.com")
            .build();

    ContactsDto contactsDto = ContactsDto.builder()
            .id(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"))
            .phone("89144033422")
            .email("nikitaivanovitc@gmail.com")
            .email("nikitaivanovitc@gmail.com")
            .build();

    Employee employee = Employee.builder()
            .id(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"))
            .firstName("firstName")
            .lastName("LastName")
            .description("descriptions")
            .characteristics(Collections.singletonList("characteristics"))
            .post(post)
            .contacts(contacts)
            .jobType(JobType.CONTRACT)
            .build();

    EmployeeDto employeeDto = EmployeeDto.builder()
            .id(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"))
            .firstName("firstName")
            .lastName("LastName")
            .description("descriptions")
            .characteristics(Collections.singletonList("characteristics"))
            .post(postDto)
            .contacts(contactsDto)
            .jobType(JobType.CONTRACT)
            .build();

    CreateEmployeeDto createEmployeeDto = CreateEmployeeDto.builder()
            .firstName("firstName")
            .lastName("LastName")
            .description("descriptions")
            .characteristics(Collections.singletonList("characteristics"))
            .post(postDto)
            .contacts(contactsDto)
            .jobType(JobType.CONTRACT)
            .build();

    CreateEmployeeArgument createEmployeeArgument = CreateEmployeeArgument.builder()
            .firstName(employee.getFirstName())
            .lastName(employee.getLastName())
            .description(employee.getDescription())
            .characteristics(employee.getCharacteristics())
            .post(postDto)
            .contacts(contactsDto)
            .jobType(employee.getJobType())
            .build();

    UpdateEmployeeArgument updateEmployee = UpdateEmployeeArgument.builder()
            .id(employee.getId())
            .firstName(employee.getFirstName())
            .lastName(employee.getLastName())
            .description(employee.getDescription())
            .characteristics(employee.getCharacteristics())
            .post(postDto)
            .contacts(contactsDto)
            .jobType(employee.getJobType())
            .build();

    @Test
    void CreateEmployeeArgument() {
        //Arrange
        CreateEmployeeArgument createEmployeeArgument = CreateEmployeeArgument.builder()
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .description(employee.getDescription())
                .characteristics(employee.getCharacteristics())
                .post(postDto)
                .contacts(contactsDto)
                .jobType(employee.getJobType())
                .build();

        //Act
        employeeService.createEmployee(createEmployeeArgument);
        Optional<UUID> pk = inMemoryEmployeeCard.getList().keySet().stream().findFirst();
        employee.setId(pk.get());
        Employee employeeResult = inMemoryEmployeeCard.get(pk.get());

        //Assert
        Assertions.assertEquals(employeeResult, employee);
    }

    @Test
    void UpdateEmployeeArgument() {
        //Arrange

        //Act
        employeeService.updateEmployee(updateEmployee.getId(), updateEmployee);
        Employee employeeResult = inMemoryEmployeeCard.get(updateEmployee.getId());
        //Assert
        Assertions.assertEquals(employeeResult, employee);
    }

    @Test
    void getById() {
        //Arrange
        inMemoryEmployeeCard.put(employee.getId(), employee);

        //Act
        Employee employee = employeeService.getById(employeeDto.getId());

        //Assert
        Assertions.assertEquals(employee, employee);
    }

    @Test
    void getList() {
        //Arrange
        inMemoryEmployeeCard.getList().clear();
        inMemoryEmployeeCard.put(employee.getId(), employee);
        HashMap storage = new HashMap();
        storage.put(employee.getId(), employee);

        //Act
        HashMap<UUID, Employee> employeesResult = employeeService.getList();

        //Assert
        Assertions.assertEquals(employeesResult, storage);
    }

    @Test
    void remove() {
        //Arrange
        inMemoryEmployeeCard.put(employee.getId(), employee);

        //Act
        employeeService.remove(employee.getId());
        Employee employeeResult = inMemoryEmployeeCard.get(employee.getId());

        //Assert
        Assertions.assertEquals(employeeResult, null);
    }
}