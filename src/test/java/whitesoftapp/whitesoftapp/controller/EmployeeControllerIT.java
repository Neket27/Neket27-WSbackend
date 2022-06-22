package whitesoftapp.whitesoftapp.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;
import whitesoftapp.whitesoftapp.controller.employee.EmployeeController;
import whitesoftapp.whitesoftapp.model.Contacts;
import whitesoftapp.whitesoftapp.model.Employee;
import whitesoftapp.whitesoftapp.model.JobType;
import whitesoftapp.whitesoftapp.model.Post;
import whitesoftapp.whitesoftapp.model.dtos.contacts.ContactsDto;
import whitesoftapp.whitesoftapp.model.dtos.employee.EmployeeDto;
import whitesoftapp.whitesoftapp.model.dtos.employee.CreateEmployeeDto;
import whitesoftapp.whitesoftapp.model.dtos.employee.UpdateEmployeeDto;
import whitesoftapp.whitesoftapp.model.dtos.post.PostDto;
import whitesoftapp.whitesoftapp.model.dtos.response.ResponseDto;
import whitesoftapp.whitesoftapp.repository.InMemoryEmployeeCard;

import java.util.Collections;
import java.util.HashMap;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EmployeeControllerIT {

    @Autowired
    private WebTestClient webClient;
    @Autowired
    private EmployeeController employeeController;
    @Autowired
    private InMemoryEmployeeCard inMemoryEmployeeCard;

    Post post = new Post(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"), "newPost");
    PostDto postDto = new PostDto(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"), "newPost");

    Contacts contacts =Contacts.builder()
            .id(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"))
            .phone("89144033422")
            .email("nikitaivanovitc@gmail.com")
            .email("nikitaivanovitc@gmail.com")
            .build();

    ContactsDto contactsDto =ContactsDto.builder()
            .id(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"))
            .phone("89144033422")
            .email("nikitaivanovitc@gmail.com")
            .email("nikitaivanovitc@gmail.com")
            .build();

    EmployeeDto employeeDto = EmployeeDto.builder()
            .id(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"))
            .firstName("firstName")
            .lastName("LastName")
            .description("descriptions")
            .characteristics(Collections.singletonList("characteristics"))
            .postDto(postDto)
            .contactsDto(contactsDto)
            .jobType(JobType.CONTRACT)
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

    CreateEmployeeDto createEmployeeDto = CreateEmployeeDto.builder()
            .firstName("firstName")
            .lastName("LastName")
            .description("descriptions")
            .characteristics(Collections.singletonList("characteristics"))
            .postDto(postDto)
            .contactsDto(contactsDto)
            .jobType(JobType.CONTRACT)
            .build();

    UpdateEmployeeDto updateEmployeeDto = UpdateEmployeeDto.builder()
            .id(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"))
            .firstName("firstName")
            .lastName("LastName")
            .description("descriptions")
            .characteristics(Collections.singletonList("characteristics"))
            .postId(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"))
            .contactsId(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"))
            .jobType(JobType.CONTRACT)
            .build();


    @Test
    void create() {

        //Arrange
        EmployeeDto resultEmployeeDto = webClient.post()
                .uri("/employees/create")
                .bodyValue(employeeDto)
                .exchange()
                //Act
                .expectStatus()
                .isOk()
                .expectBody(EmployeeDto.class)
                .returnResult()
                .getResponseBody();

        //Assert
        Assertions.assertEquals(resultEmployeeDto.getFirstName(), employeeDto.getFirstName());
        Assertions.assertEquals(resultEmployeeDto.getLastName(), employeeDto.getLastName());
        Assertions.assertEquals(resultEmployeeDto.getDescription(), resultEmployeeDto.getDescription());
        Assertions.assertEquals(resultEmployeeDto.getCharacteristics(), employeeDto.getCharacteristics());
        Assertions.assertEquals(resultEmployeeDto.getPostDto(), employeeDto.getPostDto());
        Assertions.assertEquals(resultEmployeeDto.getContactsDto(), employeeDto.getContactsDto());
        Assertions.assertEquals(resultEmployeeDto.getJobType(), employeeDto.getJobType());

    }

    @Test
    void update() {
        //Arrange
        EmployeeDto resultResponseDto = webClient.post()
                .uri("/employees/create")
                .bodyValue(createEmployeeDto)
                .exchange()
                //Act
                .expectStatus()
                .isOk()
                .expectBody(EmployeeDto.class)
                .returnResult()
                .getResponseBody();

        //Assert
        Assertions.assertEquals(resultResponseDto.getFirstName(), employeeDto.getFirstName());
        Assertions.assertEquals(resultResponseDto.getLastName(), employeeDto.getLastName());
        Assertions.assertEquals(resultResponseDto.getDescription(), resultResponseDto.getDescription());
        Assertions.assertEquals(resultResponseDto.getCharacteristics(), employeeDto.getCharacteristics());
        Assertions.assertEquals(resultResponseDto.getPostDto(), employeeDto.getPostDto());
        Assertions.assertEquals(resultResponseDto.getContactsDto(), employeeDto.getContactsDto());
        Assertions.assertEquals(resultResponseDto.getJobType(), employeeDto.getJobType());
    }

    @Test
    void getById() {
        //Arrange
        inMemoryEmployeeCard.put(employee.getId(), employee);
        ResponseDto responseDTO = ResponseDto.builder()
                .status(HttpStatus.OK.toString())
                .body(employeeDto)
                .build();

        ResponseDto<EmployeeDto> resultResponseDTO = webClient.get().uri("/employees/{id}", "854ef89d-6c27-4635-926d-894d76a81707")
                .exchange()
                //Act
                .expectStatus()
                .isOk()
                .expectBody(new ParameterizedTypeReference<ResponseDto<EmployeeDto>>() {
                })
                .returnResult()
                .getResponseBody();

        //Assert
        Assertions.assertEquals(resultResponseDTO, responseDTO);
    }

    @Test
    void getList() {
        //Arrange
        inMemoryEmployeeCard.getList().clear();
        inMemoryEmployeeCard.put(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"), employee);

        HashMap resultEmployee = webClient.get()
                .uri("/employees/list")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(HashMap.class)
                .returnResult()
                .getResponseBody();

        //Assert
        Assertions.assertNotEquals(resultEmployee, null);
    }

    @Test
    void remove() {
        //Arrange
        inMemoryEmployeeCard.add(employee);

        //Act
        employeeController.remove(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"));
        Employee employee = inMemoryEmployeeCard.get(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"));

        //Assert
        assertThat(employee).isEqualTo(null);
    }

    @Test
    void createArg() {
        //Arrange
        EmployeeDto resultEmployeeDto = webClient.post()
                .uri("/employees/create")
                .bodyValue(createEmployeeDto)
                .exchange()
                //Act
                .expectStatus()
                .isOk()
                .expectBody(EmployeeDto.class)
                .returnResult()
                .getResponseBody();

        //Assert
        Assertions.assertEquals(resultEmployeeDto.getFirstName(), employeeDto.getFirstName());
        Assertions.assertEquals(resultEmployeeDto.getLastName(), employeeDto.getLastName());
        Assertions.assertEquals(resultEmployeeDto.getDescription(), employee.getDescription());
        Assertions.assertEquals(resultEmployeeDto.getCharacteristics(), employeeDto.getCharacteristics());
        Assertions.assertEquals(resultEmployeeDto.getPostDto(), employeeDto.getPostDto());
        Assertions.assertEquals(resultEmployeeDto.getContactsDto(), employeeDto.getContactsDto());
        Assertions.assertEquals(resultEmployeeDto.getJobType(), employeeDto.getJobType());
    }

    @Test
    void updateArg() {
        EmployeeDto resultEmployeeDto = webClient.post()
                .uri("/employees/update/{id}", "854ef89d-6c27-4635-926d-894d76a81707")
                .bodyValue(updateEmployeeDto)
                .exchange()
                //Act
                .expectStatus()
                .isOk()
                .expectBody(EmployeeDto.class)
                .returnResult()
                .getResponseBody();

        //Assert
        assertThat(resultEmployeeDto).isEqualTo(employeeDto);
    }
}