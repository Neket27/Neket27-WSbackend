package whitesoftapp.whitesoftapp.controller.employee;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import whitesoftapp.whitesoftapp.action.CreateEmployeeArgumentAction;
import whitesoftapp.whitesoftapp.action.UpdateEmployeeArgumentAction;
import whitesoftapp.whitesoftapp.model.dtos.response.ResponseDto;
import whitesoftapp.whitesoftapp.model.dtos.employee.EmployeeDto;
import whitesoftapp.whitesoftapp.model.dtos.employee.CreateEmployeeDto;
import whitesoftapp.whitesoftapp.model.dtos.employee.UpdateEmployeeDto;
import whitesoftapp.whitesoftapp.service.employee.EmployeeService;

import javax.validation.Valid;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final CreateEmployeeArgumentAction createEmployeeArgumentAction;
    private final UpdateEmployeeArgumentAction updateEmployeeArgumentAction;

    @ApiOperation("Создание нового работника Arg")
    @PostMapping("/create")
    public EmployeeDto create(@Valid @RequestBody CreateEmployeeDto createEmployeeDto) {
        return employeeService.createEmployee(createEmployeeArgumentAction.create(createEmployeeDto));
    }

    @ApiOperation("Обновление информации сотрудника Arg")
    @PostMapping(value = "/update/{id}")
    public void update(@PathVariable UUID id, @RequestBody UpdateEmployeeDto updateEmployeeDto) {
        employeeService.updateEmployee(id, updateEmployeeArgumentAction.update(updateEmployeeDto));
    }

    @ApiOperation("Получение сотрудника по id")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getById(@PathVariable UUID id) {
        ResponseDto responseDTO = ResponseDto.builder()
                .status(HttpStatus.OK.toString())
                .body(employeeService.getById(id))
                .build();
        return ResponseEntity.ok(responseDTO);
    }

    @ApiOperation("Вывод списка сотрудников")
    @GetMapping("/list")
    public ResponseEntity<ResponseDto> getList() {
        ResponseDto responseDTO = ResponseDto.builder()
                .status(HttpStatus.OK.toString())
                .body(employeeService.getList()).build();
        return ResponseEntity.ok(responseDTO);
    }

    @ApiOperation("Удаление работника по id")
    @GetMapping("/remove/{id}")
    public void remove(@PathVariable UUID id) {
        employeeService.remove(id);
    }

}







