package whitesoftapp.whitesoftapp.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import whitesoftapp.whitesoftapp.apiExceptionHandler.ResponseDTO;
import whitesoftapp.whitesoftapp.model.dtos.EmployeeDto;
import whitesoftapp.whitesoftapp.service.EmployeeService;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
@Validated
public class EmployeeController {

    private final EmployeeService employeeService;

    @ApiOperation("Создание нового работника")
    @PostMapping("/employee/create")
    public EmployeeDto create(@RequestParam(value = "id")UUID id,@RequestBody EmployeeDto employeeDto) {
        return employeeService.createEmployee(id,employeeDto);
    }

    @ApiOperation("Обновление информации сотрудника")
    @PostMapping("employee/update")
    public void update(@RequestParam UUID id, @RequestBody EmployeeDto updateEmployeeDto) {
        employeeService.updateEmployee(id, updateEmployeeDto);
    }

    @ApiOperation("Получение сотрудника по id")
    @GetMapping("/employees/{id}")
    public ResponseEntity<ResponseDTO> getById(@PathVariable UUID id)  {
        ResponseDTO responseDTO = ResponseDTO.builder()
                .status(HttpStatus.OK.toString())
                .body(employeeService.getById(id)).build();

        return ResponseEntity.ok(responseDTO);
    }

    @ApiOperation("Вывод списка сотрудников")
    @GetMapping("/employees/list")
    public ResponseEntity<ResponseDTO> getList() {
        ResponseDTO responseDTO = ResponseDTO.builder()
                .status(HttpStatus.OK.toString())
                .body(employeeService.getList()).build();
        return ResponseEntity.ok(responseDTO);
    }

    public void remove(UUID id) {
        employeeService.remove(id);
    }

}







