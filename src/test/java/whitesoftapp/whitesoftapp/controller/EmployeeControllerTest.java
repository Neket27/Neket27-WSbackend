package whitesoftapp.whitesoftapp.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import whitesoftapp.whitesoftapp.apiExceptionHandler.ResponseDTO;
import whitesoftapp.whitesoftapp.model.Employee;
import whitesoftapp.whitesoftapp.model.Post;
import whitesoftapp.whitesoftapp.model.dtos.EmployeeDto;
import whitesoftapp.whitesoftapp.repository.InMemoryEmployeeCard;

import java.util.Collections;
import java.util.HashMap;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class EmployeeControllerTest {

    @Autowired
    private EmployeeController employeeController;
    @Autowired
    private InMemoryEmployeeCard inMemoryEmployeeCard;

    Post post = new Post(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"), "newPost");
    EmployeeDto employeeDto = EmployeeDto.builder()
            .id(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"))
            .firstName("firstName")
            .lastName("LastName")
            .description("descriptions")
            .characteristics(Collections.singletonList("characterustics"))
            .post(post)
            .build();

    Employee employee = Employee.builder()
            .id(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"))
            .firstName("firstName")
            .lastName("LastName")
            .description("descriptions")
            .characteristics(Collections.singletonList("characterustics"))
            .post(post)
            .build();

    ResponseDTO responseDTO = ResponseDTO.builder()
            .status("OKE")
            .body(employeeDto)
            .build();

    @Test
    void create() {
        //Arrange

        //Act
        employeeController.create(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"), employeeDto);

        //Assert
        assertThat(inMemoryEmployeeCard.get(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"))).isEqualTo(employee);
    }

    @Test
    void update() {
        //Arrange

        //Act
        //еслли записи не будет с таким id, то она создастся, иначе обновится.
        employeeController.update(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"), employeeDto);

        //Assert
        assertThat(inMemoryEmployeeCard.get(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"))).isEqualTo(employee);
    }

    @Test
    void getById() {
        //Arrange
        inMemoryEmployeeCard.put(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"), employee);

        //Act
        ResponseEntity<ResponseDTO> responseDto = employeeController.getById(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"));

        //Assert
        assertThat(responseDto.getBody().getBody()).isEqualTo(employeeDto);

    }

    @Test
    void getList() {
        //Arrange
        inMemoryEmployeeCard.getList().clear();
        HashMap<UUID, EmployeeDto> storageEmployeesDto = new HashMap<>();
        inMemoryEmployeeCard.put(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"), employee);
        storageEmployeesDto.put(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"), employeeDto);

        ResponseDTO responseDTO = ResponseDTO.builder()
                .status(HttpStatus.OK.toString())
                .body(storageEmployeesDto).build();
        //Act

        //Assert
        assertThat(employeeController.getList()).isEqualTo(ResponseEntity.ok(responseDTO));
    }

    @Test
    void remove() {
        //Arrange
        inMemoryEmployeeCard.put(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"), employee);

        //Act
        employeeController.remove(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"));

        //Assert
        assertThat(inMemoryEmployeeCard.get(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"))).isEqualTo(null);
    }
}