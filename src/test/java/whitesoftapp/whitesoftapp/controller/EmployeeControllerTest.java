package whitesoftapp.whitesoftapp.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import whitesoftapp.whitesoftapp.model.Contacts;
import whitesoftapp.whitesoftapp.model.Employee;
import whitesoftapp.whitesoftapp.model.JobType;
import whitesoftapp.whitesoftapp.model.Post;
import whitesoftapp.whitesoftapp.model.dtos.employee.CreateEmployeeDto;
import whitesoftapp.whitesoftapp.model.dtos.employee.EmployeeDto;
import whitesoftapp.whitesoftapp.model.dtos.employee.UpdateEmployeeDto;
import whitesoftapp.whitesoftapp.model.dtos.post.PostDto;
import whitesoftapp.whitesoftapp.repository.InMemoryEmployeeCard;

import java.util.Collections;
import java.util.HashMap;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EmployeeControllerTest {

    @Autowired
    private WebTestClient webClient;
    @Autowired
    private EmployeeController employeeController;
    @Autowired
    private InMemoryEmployeeCard inMemoryEmployeeCard;

    Post post = new Post(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"), "newPost");
    PostDto postDto = new PostDto(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"), "newPost");

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

    CreateEmployeeDto createEmployeeDto = CreateEmployeeDto.builder()
            .firstName("firstName")
            .lastName("LastName")
            .description("descriptions")
            .characteristics(Collections.singletonList("characteristics"))
            .post(post)
            .contacts(new Contacts())
            .jobType(new JobType("1", "2", "3", "4", "5"))
            .build();

    UpdateEmployeeDto updateEmployeeDto = UpdateEmployeeDto.builder()
            .id(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"))
            .firstName("firstName")
            .lastName("LastName")
            .description("descriptions")
            .characteristics(Collections.singletonList("characteristics"))
            .post(post)
            .contacts(new Contacts())
            .jobType(new JobType("1", "2", "3", "4", "5"))
            .build();


    @Test
    void create() {

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
        Assertions.assertEquals(resultEmployeeDto.getDescription(), resultEmployeeDto.getDescription());
        Assertions.assertEquals(resultEmployeeDto.getCharacteristics(), employeeDto.getCharacteristics());
        Assertions.assertEquals(resultEmployeeDto.getPost(), employeeDto.getPost());
        Assertions.assertEquals(resultEmployeeDto.getContacts(), employeeDto.getContacts());
        Assertions.assertEquals(resultEmployeeDto.getJobType(), employeeDto.getJobType());

    }

    @Test
    void update() {

           //Act
        //еслли записи не будет с таким id, то она создастся, иначе обновится.
        employeeController.update(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"), employeeDto);

        //Assert
        assertThat(inMemoryEmployeeCard.get(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"))).isEqualTo(employee);
    }

    @Test
    void getById() {
        //Arrange
        inMemoryEmployeeCard.put(employee.getId(), employee);

        EmployeeDto resultEmployeeDto = webClient.get().uri("/employees/getById/{id}", "854ef89d-6c27-4635-926d-894d76a81707")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(EmployeeDto.class)
                .returnResult()
                .getResponseBody();

        //Assert
        Assertions.assertEquals(resultEmployeeDto,employeeDto);

    }

    @Test
    void getList() {
        //Arrange
        inMemoryEmployeeCard.getList().clear();

        HashMap resultEmployee = webClient.get()
                .uri("/employees/list")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(HashMap.class)
                .returnResult()
                .getResponseBody();

        //Возращается resultEmployee: {message=Success!, body={}, status=200 OK}
        Assertions.assertEquals(resultEmployee,null);

    }

    @Test
    void remove() {
        //Arrange
        inMemoryEmployeeCard.add(employee);

        //Act
        employeeController.remove(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"));

        //Assert
        assertThat(inMemoryEmployeeCard.get(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"))).isEqualTo(null);
    }

    @Test
    void createArg() {

        //Act
        employeeController.create(createEmployeeDto);

        //Assert
        assertThat(inMemoryEmployeeCard.get(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"))).isEqualTo(employee);
    }

    @Test
    void updateArg() {

        //Act
        employeeController.update(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"), updateEmployeeDto);

        //Assert
        assertThat(inMemoryEmployeeCard.get(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"))).isEqualTo(employee);
    }
}