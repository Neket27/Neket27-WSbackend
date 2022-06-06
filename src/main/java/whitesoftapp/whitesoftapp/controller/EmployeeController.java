package whitesoftapp.whitesoftapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import whitesoftapp.whitesoftapp.apiExceptionHandler.ResponseDTO;
import whitesoftapp.whitesoftapp.model.dtos.CreateEmployeeDto;
import whitesoftapp.whitesoftapp.model.dtos.DtoEntity;
import whitesoftapp.whitesoftapp.model.dtos.UpdateEmployeeDto;
import whitesoftapp.whitesoftapp.service.EmployeeService;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class EmployeeController {

    private final EmployeeService employeeServise;

    public DtoEntity create(CreateEmployeeDto createEmployeeDto) {
        return employeeServise.createEmployee(createEmployeeDto);
    }

    public void update(UUID id, UpdateEmployeeDto updateEmployeeDto) {
        employeeServise.updateEmployee(id, updateEmployeeDto);
    }

    @GetMapping("/employees/{id}")
    public DtoEntity getById(@PathVariable UUID id) throws Exception {
        return employeeServise.getById(id);
    }

    @GetMapping("/employees/list")
    public ResponseEntity<DtoEntity> getList() {
        DtoEntity responseDTO = ResponseDTO.builder()
                .status(HttpStatus.OK.toString())
                .body(employeeServise.getList()).build();
        return ResponseEntity.ok(responseDTO);
    }

    public void remove(UUID id) {
        employeeServise.remove(id);
    }

}







