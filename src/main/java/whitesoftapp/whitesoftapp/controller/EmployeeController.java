package whitesoftapp.whitesoftapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import whitesoftapp.whitesoftapp.apiExceptionHandler.ResponseDTO;
import whitesoftapp.whitesoftapp.model.dtos.EmployeeDto;
import whitesoftapp.whitesoftapp.service.EmployeeService;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
@Validated
public class EmployeeController {

    private final EmployeeService employeeServise;

    public EmployeeDto create(UUID id,@RequestBody EmployeeDto employeeDto) {
        return employeeServise.createEmployee(id,employeeDto);
    }

    public void update(UUID id, EmployeeDto updateEmployeeDto) {
        employeeServise.updateEmployee(id, updateEmployeeDto);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<ResponseDTO> getById(@PathVariable UUID id)  {
        ResponseDTO responseDTO = ResponseDTO.builder()
                .status(HttpStatus.OK.toString())
                .body(employeeServise.getById(id)).build();
        System.out.println("ResponseDTO: "+responseDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/employees/list")
    public ResponseEntity<ResponseDTO> getList() {
        ResponseDTO responseDTO = ResponseDTO.builder()
                .status(HttpStatus.OK.toString())
                .body(employeeServise.getList()).build();
        return ResponseEntity.ok(responseDTO);
    }

    public void remove(UUID id) {
        employeeServise.remove(id);
    }

}







