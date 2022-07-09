package whitesoftapp.whitesoftapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import whitesoftapp.controller.employee.EmployeeController;
import whitesoftapp.model.Employee;
import whitesoftapp.model.Post;
import whitesoftapp.model.dtos.employee.CreateEmployeeDto;
import whitesoftapp.model.dtos.employee.EmployeeDto;
import whitesoftapp.model.dtos.employee.UpdateEmployeeDto;
import whitesoftapp.model.dtos.post.PostDto;
import whitesoftapp.repository.InMemoryEmployeeCard;
import whitesoftapp.repository.InMemoryPost;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EmployeeControllerIT {

    @Autowired
    private WebTestClient webClient;
    @Autowired
    private EmployeeController employeeController;
    @Autowired
    private InMemoryEmployeeCard inMemoryEmployeeCard;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    InMemoryPost inMemoryPost;

    UUID id = UUID.fromString("3fa85f64-5717-4562-b3fc-2c963f66afa6");

    private Employee employeeExpected;
    private EmployeeDto expectedEmployeeDto;
    private CreateEmployeeDto createEmployeeDto;
    private UpdateEmployeeDto updateEmployeeDto;

    @BeforeEach
    private void setData() throws IOException {

        createEmployeeDto = date("CreateEmployeeDto.json", CreateEmployeeDto.class);
        employeeExpected = date("Employee.json", Employee.class);
        expectedEmployeeDto = date("Employee.json", EmployeeDto.class);
        expectedEmployeeDto = date("Employee.json", EmployeeDto.class);
        updateEmployeeDto = date("Employee.json", UpdateEmployeeDto.class);

        employeeExpected.setPost(new Post(UUID.fromString("3fa85f64-5717-4562-b3fc-2c963f66afa6"), "string"));
        expectedEmployeeDto.setPost(new PostDto(UUID.fromString("3fa85f64-5717-4562-b3fc-2c963f66afa6"), "string"));
        updateEmployeeDto.setPost(new PostDto(UUID.fromString("3fa85f64-5717-4562-b3fc-2c963f66afa6"), "string"));
        inMemoryEmployeeCard.put(UUID.fromString("3fa85f64-5717-4562-b3fc-2c963f66afa6"), employeeExpected);
    }

    private <T> T date(String file, Class<T> fileClass) throws IOException {
        return objectMapper.readValue(new File(Objects.requireNonNull(EmployeeControllerIT.class
                                .getClassLoader()
                                .getResource(file))
                        .getFile()),
                fileClass);
    }

    @Test
    void create() {
        //Arrange
        inMemoryPost.create(UUID.fromString("3fa85f64-5717-4562-b3fc-2c963f66afa6"), "string");

        //Act
        EmployeeDto actual = webClient.post()
                .uri("/employees/create")
                .bodyValue(createEmployeeDto)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(EmployeeDto.class)
                .returnResult()
                .getResponseBody();

        //Assert
        Assertions.assertEquals(actual.getFirstName(), expectedEmployeeDto.getFirstName());
        Assertions.assertEquals(actual.getLastName(), expectedEmployeeDto.getLastName());
        Assertions.assertEquals(actual.getDescription(), actual.getDescription());
        Assertions.assertEquals(actual.getCharacteristics(), expectedEmployeeDto.getCharacteristics());
        Assertions.assertEquals(actual.getPost(), expectedEmployeeDto.getPost());
        Assertions.assertEquals(actual.getContacts(), expectedEmployeeDto.getContacts());
        Assertions.assertEquals(actual.getJobType(), expectedEmployeeDto.getJobType());

    }

    @Test
    void update() {
        //Act
        EmployeeDto actual = webClient.post()
                .uri("/employees/update/{id}", id)
                .bodyValue(updateEmployeeDto)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(EmployeeDto.class)
                .returnResult()
                .getResponseBody();

        //Assert
        Assertions.assertEquals(actual.getFirstName(), expectedEmployeeDto.getFirstName());
        Assertions.assertEquals(actual.getLastName(), expectedEmployeeDto.getLastName());
        Assertions.assertEquals(actual.getDescription(), actual.getDescription());
        Assertions.assertEquals(actual.getCharacteristics(), expectedEmployeeDto.getCharacteristics());
        Assertions.assertEquals(actual.getPost(), expectedEmployeeDto.getPost());
        Assertions.assertEquals(actual.getContacts(), expectedEmployeeDto.getContacts());
        Assertions.assertEquals(actual.getJobType(), expectedEmployeeDto.getJobType());

    }

    @Test
    void getById() {
        //Act
        EmployeeDto result = webClient.get().uri("/employees/{id}", id)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(EmployeeDto.class)
                .returnResult()
                .getResponseBody();

        //Assert
        Assertions.assertEquals(result, expectedEmployeeDto);
    }

    @Test
    void getList() {
        //Arrange
        inMemoryEmployeeCard.getList().clear();
        inMemoryEmployeeCard.put(id, employeeExpected);

        //Act
        HashMap resultEmployee = webClient.get()
                .uri("/employees/list")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(HashMap.class)
                .returnResult()
                .getResponseBody();

        HashMap<UUID, Employee> listEmployee = new HashMap<>();
        listEmployee.put(id, employeeExpected);

        //Assert
        Assertions.assertNotEquals(resultEmployee, listEmployee);
    }

    @Test
    void remove() {
        //Arrange
        inMemoryEmployeeCard.add(employeeExpected);

        //Act
        webClient.get().uri("employees/remove/{id}", id)
                .exchange()
                .expectStatus()
                .isOk();

        Employee result = inMemoryEmployeeCard.get(id);

        //Assert
        Assertions.assertNull(result);
    }

}