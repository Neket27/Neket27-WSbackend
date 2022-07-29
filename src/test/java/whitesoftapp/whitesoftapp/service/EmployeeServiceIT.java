package whitesoftapp.whitesoftapp.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
import whitesoftapp.repository.EmployeeRepository;
import whitesoftapp.service.employee.EmployeeService;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
class EmployeeServiceIT {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeRepository employeeRepository;

    Post post = new Post(UUID.fromString("3fa85f64-5717-4562-b3fc-2c963f66afa6"), "string");
    PostDto postDto = new PostDto(UUID.fromString("3fa85f64-5717-4562-b3fc-2c963f66afa6"), "string");


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
                                        .id(UUID.fromString("3fa85f64-5717-4562-b3fc-2c963f66afa6"))
                                        .firstName("firstName")
                                        .lastName("LastName")
                                        .description("descriptions")
                                        .characteristics(Collections.singletonList("characteristics"))
                                        .post(post)
                                        .contacts(contacts)
                                        .jobType(JobType.CONTRACT)
                                        .build();

    EmployeeDto employeeDto = EmployeeDto.builder()
                                         .id(UUID.fromString("3fa85f64-5717-4562-b3fc-2c963f66afa6"))
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
                                                           .postId(UUID.fromString("3fa85f64-5717-4562-b3fc-2c963f66afa6"))
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
                                                                  .postId(employeeExpected.getPost().getId())
                                                                  .contacts(contactsDto)
                                                                  .jobType(employeeExpected.getJobType())
                                                                  .build();

    @BeforeEach
    void setData() {
        employeeRepository.deleteAll();
        employeeRepository.save(employeeExpected);
        Employee e = employeeRepository.findAll().get(0);
        employeeExpected.setId(e.getId());
        employeeDto.setId(e.getId());
        updateEmployee.setId(e.getId());

    }

    @Test
    void CreateEmployeeArgument() {
        //Arrange
        CreateEmployeeArgument createEmployeeArgument = CreateEmployeeArgument.builder()
                                                                              .firstName(employeeExpected.getFirstName())
                                                                              .lastName(employeeExpected.getLastName())
                                                                              .description(employeeExpected.getDescription())
                                                                              .characteristics(employeeExpected.getCharacteristics())
                                                                              .postId(employeeExpected.getPost().getId())
                                                                              .contacts(contactsDto)
                                                                              .jobType(employeeExpected.getJobType())
                                                                              .build();

        //Act
        employeeService.create(createEmployeeArgument);
        UUID pk = employeeRepository.findAll().get(0).getId();
        employeeExpected.setId(pk);
        Employee actual = employeeRepository.findById(pk).orElseThrow(() -> new RuntimeException("Нет работника с таким id"));

        //Assert
        assertThat(actual).usingComparatorForFields((x, y) -> 0, "field1", "field2").isEqualToComparingFieldByFieldRecursively(employeeExpected);
    }

    @Test
    void UpdateEmployeeArgument() {
        //Act
        employeeService.update(updateEmployee.getId(), updateEmployee);
        Employee actual = employeeRepository.findById(updateEmployee.getId()).orElseThrow(() -> new RuntimeException("Нет работника стаким id"));
        //Assert
        assertThat(actual).usingComparatorForFields((x, y) -> 0, "field1", "field2").isEqualToComparingFieldByFieldRecursively(employeeExpected);
    }

    @Test
    void getById() {
        //Act
        Employee e = employeeRepository.findAll().get(0);
        Employee employee = employeeService.getById(e.getId());

        //Assert
        assertThat(employee).usingComparatorForFields((x, y) -> 0, "field1", "field2")
                            .isEqualToComparingFieldByFieldRecursively(employeeExpected);
    }

    @Test
    void getList() {
        //Act
        List<Employee> actual = employeeService.getList();

        //Assert
        assertThat(actual.get(0)).usingComparatorForFields((x, y) -> 0, "field1", "field2").isEqualToComparingFieldByFieldRecursively(employeeExpected);
    }

    @Test
    void remove() {
        //Act
        employeeService.remove(employeeExpected.getId());
        List<Employee> actual = employeeRepository.findAll();

        //Assert
        Assertions.assertEquals(actual.size(), 0);
    }
}