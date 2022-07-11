package whitesoftapp.whitesoftapp.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
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

import java.util.*;


@SpringBootTest
class EmployeeServiceIT {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private InMemoryEmployeeCard inMemoryEmployeeCard;

    Post post = new Post(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"), "newPost");
    PostDto postDto = new PostDto(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"), "newPost");


    Contacts contacts = Contacts.builder()
            .phone("89144033422")
            .email("nikitaivanovitc@gmail.com")
            .email("nikitaivanovitc@gmail.com")
            .build();

    ContactsDto contactsDto = ContactsDto.builder()
            .phone("89144033422")
            .email("nikitaivanovitc@gmail.com")
            .email("nikitaivanovitc@gmail.com")
            .build();

    Employee employeeExpected = Employee.builder()
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
            .postId(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"))
            .contacts(contactsDto)
            .jobType(JobType.CONTRACT)
            .build();

    CreateEmployeeArgument createEmployeeArgument = CreateEmployeeArgument.builder()
            .firstName(employeeExpected.getFirstName())
            .lastName(employeeExpected.getLastName())
            .description(employeeExpected.getDescription())
            .characteristics(employeeExpected.getCharacteristics())
            .postId(employeeExpected.getId())
            .contacts(contactsDto)
            .jobType(employeeExpected.getJobType())
            .build();

    UpdateEmployeeArgument updateEmployee = UpdateEmployeeArgument.builder()
            .id(employeeExpected.getId())
            .firstName(employeeExpected.getFirstName())
            .lastName(employeeExpected.getLastName())
            .description(employeeExpected.getDescription())
            .characteristics(employeeExpected.getCharacteristics())
            .post(postDto)
            .contacts(contactsDto)
            .jobType(employeeExpected.getJobType())
            .build();

    @Test
    void CreateEmployeeArgument() {
        //Arrange
        CreateEmployeeArgument createEmployeeArgument = CreateEmployeeArgument.builder()
                .firstName(employeeExpected.getFirstName())
                .lastName(employeeExpected.getLastName())
                .description(employeeExpected.getDescription())
                .characteristics(employeeExpected.getCharacteristics())
                .postId(employeeExpected.getId())
                .contacts(contactsDto)
                .jobType(employeeExpected.getJobType())
                .build();

        //Act
        employeeService.create(createEmployeeArgument);
        Optional<UUID> pk = inMemoryEmployeeCard.getMap().keySet().stream().findFirst();
        employeeExpected.setId(pk.get());
        Employee actual = inMemoryEmployeeCard.get(pk.get());

        //Assert
        assertThat(actual).usingComparatorForFields((x, y) -> 0, "field1", "field2").isEqualToComparingFieldByFieldRecursively(employeeExpected);
    }

    @Test
    void UpdateEmployeeArgument() {
        //Arrange

        //Act
        employeeService.update(updateEmployee.getId(), updateEmployee);
        Employee actual = inMemoryEmployeeCard.get(updateEmployee.getId());
        //Assert
        assertThat(actual).usingComparatorForFields((x, y) -> 0, "field1", "field2").isEqualToComparingFieldByFieldRecursively(employeeExpected);
    }

    @Test
    void getById() {
        //Arrange
        inMemoryEmployeeCard.put(employeeExpected.getId(), employeeExpected);

        //Act
        Employee employee = employeeService.getById(employeeDto.getId());

        //Assert
        Assertions.assertEquals(employee, employee);
    }

    @Test
    void getList() {
        //Arrange
        inMemoryEmployeeCard.getMap().clear();
        inMemoryEmployeeCard.put(employeeExpected.getId(), employeeExpected);
        HashMap storage = new HashMap();
        storage.put(employeeExpected.getId(), employeeExpected);

        //Act
        Map<UUID, Employee> employeesResult = employeeService.getMap();

        //Assert
        Assertions.assertEquals(employeesResult, storage);
    }

    @Test
    void remove() {
        //Arrange
        inMemoryEmployeeCard.put(employeeExpected.getId(), employeeExpected);

        //Act
        employeeService.remove(employeeExpected.getId());
        Employee employeeResult = inMemoryEmployeeCard.get(employeeExpected.getId());

        //Assert
        Assertions.assertEquals(employeeResult, null);
    }
}