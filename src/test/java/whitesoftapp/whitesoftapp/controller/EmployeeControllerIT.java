package whitesoftapp.whitesoftapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.web.reactive.server.WebTestClient;
import whitesoftapp.controller.employee.EmployeeController;
import whitesoftapp.model.Employee;
import whitesoftapp.model.Post;
import whitesoftapp.model.dtos.employee.CreateEmployeeDto;
import whitesoftapp.model.dtos.employee.EmployeeDto;
import whitesoftapp.model.dtos.employee.UpdateEmployeeDto;
import whitesoftapp.model.dtos.post.PostDto;
import whitesoftapp.repository.EmployeeRepository;
import whitesoftapp.repository.PostRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EmployeeControllerIT {

    @Autowired
    private WebTestClient webClient;
    @Autowired
    private EmployeeController employeeController;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private PostRepository postRepository;


    private Employee employeeExpected;
    private EmployeeDto expectedEmployeeDto;
    private CreateEmployeeDto createEmployeeDto;
    private UpdateEmployeeDto updateEmployeeDto;

    UUID id;

    @BeforeEach
    private void setData() throws IOException {

        createEmployeeDto = data("CreateEmployeeDto.json", CreateEmployeeDto.class);
        employeeExpected = data("Employee.json", Employee.class);
        expectedEmployeeDto = data("Employee.json", EmployeeDto.class);
        expectedEmployeeDto = data("Employee.json", EmployeeDto.class);
        updateEmployeeDto = data("Employee.json", UpdateEmployeeDto.class);

        employeeExpected.setPost(new Post(UUID.fromString("3fa85f64-5717-4562-b3fc-2c963f66afa6"), "string"));
        expectedEmployeeDto.setPost(new PostDto(UUID.fromString("3fa85f64-5717-4562-b3fc-2c963f66afa6"), "string"));
        updateEmployeeDto.setPost(new PostDto(UUID.fromString("3fa85f64-5717-4562-b3fc-2c963f66afa6"), "string"));
        employeeRepository.deleteAll();
        employeeRepository.save(employeeExpected);
        id=employeeRepository.findAll().get(0).getId();
        updateEmployeeDto.setId(id);
    }

    private <T> T data(String file, Class<T> fileClass) throws IOException {
        File resource = new ClassPathResource(file).getFile();
        return objectMapper.readValue(new String(Files.readAllBytes(resource.toPath())),fileClass);
    }

    @Test
    void create() throws IOException {
        //Arrange
        postRepository.save(new Post(UUID.fromString("3fa85f64-5717-4562-b3fc-2c963f66afa6"), "string"));

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
        assertEquals(actual.getFirstName(), expectedEmployeeDto.getFirstName());
        assertEquals(actual.getLastName(), expectedEmployeeDto.getLastName());
        assertEquals(actual.getDescription(), actual.getDescription());
        assertEquals(actual.getCharacteristics(), expectedEmployeeDto.getCharacteristics());
        assertEquals(actual.getPost(), expectedEmployeeDto.getPost());
        assertEquals(actual.getContacts(), expectedEmployeeDto.getContacts());
        assertEquals(actual.getJobType(), expectedEmployeeDto.getJobType());
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
        assertEquals(actual.getFirstName(), expectedEmployeeDto.getFirstName());
        assertEquals(actual.getLastName(), expectedEmployeeDto.getLastName());
        assertEquals(actual.getDescription(), actual.getDescription());
        assertEquals(actual.getCharacteristics(), expectedEmployeeDto.getCharacteristics());
        assertEquals(actual.getPost(), expectedEmployeeDto.getPost());
        assertEquals(actual.getContacts(), expectedEmployeeDto.getContacts());
        assertEquals(actual.getJobType(), expectedEmployeeDto.getJobType());

    }

    @Test
    void getById() {
        //Act
        employeeRepository.save(employeeExpected);
        UUID pk=employeeRepository.findAll().get(0).getId();
        expectedEmployeeDto.setId(pk);

        EmployeeDto actual = webClient.get().uri("/employees/{id}", pk)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(EmployeeDto.class)
                .returnResult()
                .getResponseBody();

        //Assert
        assertEquals(actual, expectedEmployeeDto);
    }

    @Test
    void getList() {
        //Arrange
        employeeRepository.save(employeeExpected);

        //Act
        List resultEmployee = webClient.get()
                .uri("/employees/list")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(List.class)
                .returnResult()
                .getResponseBody();

        List<EmployeeDto> listEmployee = new ArrayList<>();
        listEmployee.add(expectedEmployeeDto);

        //Assert
        Assertions.assertNotEquals(resultEmployee, listEmployee);
    }

    @Test
    void remove() {
        //Arrange
        employeeRepository.save(employeeExpected);
        Employee employee=employeeRepository.findAll().get(0);
        UUID pk = employeeRepository.findAll().get(0).getId();
        //Act
        webClient.get().uri("employees/remove/{id}", pk)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .returnResult()
                 //Assert
                .equals(null);
    }

}