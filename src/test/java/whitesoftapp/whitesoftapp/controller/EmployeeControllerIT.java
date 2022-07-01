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
import whitesoftapp.model.dtos.employee.CreateEmployeeDto;
import whitesoftapp.model.dtos.employee.EmployeeDto;
import whitesoftapp.model.dtos.employee.UpdateEmployeeDto;
import whitesoftapp.repository.InMemoryEmployeeCard;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EmployeeControllerIT<employee> {

    @Autowired
    private WebTestClient webClient;
    @Autowired
    private EmployeeController employeeController;
    @Autowired
    private InMemoryEmployeeCard inMemoryEmployeeCard;
    @Autowired
    private ObjectMapper objectMapper;

    UUID id = UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707");

    private Employee employee;
    private EmployeeDto expectedEmployeeDto;
    private CreateEmployeeDto createEmployeeDto;
    private UpdateEmployeeDto updateEmployeeDto;

    @BeforeEach
    private void setData() throws IOException {
        String json = " {\n" +
                "    \"characteristics\": [\n" +
                "      \"string\"\n" +
                "    ],\n" +
                "    \"contacts\": {\n" +
                "      \"email\": \"string\",\n" +
                "      \"id\": \"3fa85f64-5717-4562-b3fc-2c963f66afa6\",\n" +
                "      \"phone\": \"string\",\n" +
                "      \"workEmail\": \"string\"\n" +
                "    },\n" +
                "    \"description\": \"string\",\n" +
                "    \"firstName\": \"string\",\n" +
                "    \"jobType\": \"CONTRACT\",\n" +
                "    \"lastName\": \"string\",\n" +
                "    \"post\": {\n" +
                "      \"id\": \"3fa85f64-5717-4562-b3fc-2c963f66afa6\",\n" +
                "      \"name\": \"string\"\n" +
                "    }\n" +
                "  }";

        employee = objectMapper.readValue(json, Employee.class);
        ;
        expectedEmployeeDto = objectMapper.readValue(json, EmployeeDto.class);
        createEmployeeDto = objectMapper.readValue(json, CreateEmployeeDto.class);
        updateEmployeeDto = objectMapper.readValue(json, UpdateEmployeeDto.class);
    }


    @Test
    void create() throws IOException {

        //Act
        EmployeeDto resultEmployeeDto = webClient.post()
                .uri("/employees/create")
                .bodyValue(expectedEmployeeDto)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(EmployeeDto.class)
                .returnResult()
                .getResponseBody();

        //Assert

        Assertions.assertEquals(resultEmployeeDto.getFirstName(), expectedEmployeeDto.getFirstName());
        Assertions.assertEquals(resultEmployeeDto.getLastName(), expectedEmployeeDto.getLastName());
        Assertions.assertEquals(resultEmployeeDto.getDescription(), resultEmployeeDto.getDescription());
        Assertions.assertEquals(resultEmployeeDto.getCharacteristics(), expectedEmployeeDto.getCharacteristics());
        Assertions.assertEquals(resultEmployeeDto.getPost(), expectedEmployeeDto.getPost());
        Assertions.assertEquals(resultEmployeeDto.getContacts(), expectedEmployeeDto.getContacts());
        Assertions.assertEquals(resultEmployeeDto.getJobType(), expectedEmployeeDto.getJobType());

    }

    @Test
    void update() {
        //Act
        EmployeeDto resultResponseDto = webClient.post()
                .uri("/employees/update/{id}", id)
                .bodyValue(updateEmployeeDto)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(EmployeeDto.class)
                .returnResult()
                .getResponseBody();

        //Assert
        Assertions.assertEquals(resultResponseDto.getFirstName(), expectedEmployeeDto.getFirstName());
        Assertions.assertEquals(resultResponseDto.getLastName(), expectedEmployeeDto.getLastName());
        Assertions.assertEquals(resultResponseDto.getDescription(), resultResponseDto.getDescription());
        Assertions.assertEquals(resultResponseDto.getCharacteristics(), expectedEmployeeDto.getCharacteristics());
        Assertions.assertEquals(resultResponseDto.getPost(), expectedEmployeeDto.getPost());
        Assertions.assertEquals(resultResponseDto.getContacts(), expectedEmployeeDto.getContacts());
        Assertions.assertEquals(resultResponseDto.getJobType(), expectedEmployeeDto.getJobType());
    }

    @Test
    void getById() {
        //Act
        EmployeeDto result = webClient.get().uri("/employees/{id}",id )
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
        inMemoryEmployeeCard.put(id, employee);

        //Act
        HashMap resultEmployee = webClient.get()
                .uri("/employees/list")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(HashMap.class)
                .returnResult()
                .getResponseBody();

        HashMap<UUID,Employee>listEmployee = new HashMap<>();
        listEmployee.put(id, employee);

        //Assert
        Assertions.assertNotEquals(resultEmployee,listEmployee);
    }

    @Test
    void remove() {
        //Arrange
        inMemoryEmployeeCard.add(employee);

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