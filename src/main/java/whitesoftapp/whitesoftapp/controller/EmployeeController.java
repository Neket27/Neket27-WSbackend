package whitesoftapp.whitesoftapp.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import whitesoftapp.whitesoftapp.action.CreateEmployeeArgumentAction;
import whitesoftapp.whitesoftapp.action.UpdateEmployeeArgumentAction;
import whitesoftapp.whitesoftapp.model.dtos.employee.CreateEmployeeDto;
import whitesoftapp.whitesoftapp.model.dtos.employee.EmployeeDto;
import whitesoftapp.whitesoftapp.model.dtos.employee.UpdateEmployeeDto;
import whitesoftapp.whitesoftapp.service.EmployeeService;

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
    @PostMapping("/createArg")
    public EmployeeDto create(@Valid @RequestBody CreateEmployeeDto createEmployeeDto) {
        return employeeService.createEmployee(createEmployeeArgumentAction.create(createEmployeeDto));
    }

    @ApiOperation("Создание нового работника")
    @PostMapping("/create")
    public EmployeeDto create(@Valid @RequestBody EmployeeDto employeeDto) {
        return employeeService.createEmployee(employeeDto);
    }

    @ApiOperation("Обновление информации сотрудника Arg")
    @PostMapping(value="/updateArg/{id}", consumes={"application/json"})
    public void update(@RequestParam UUID id, @RequestBody UpdateEmployeeDto updateEmployeeDto) {
        employeeService.updateEmployee(id, updateEmployeeArgumentAction.update(updateEmployeeDto));
    }

    @ApiOperation("Обновление информации сотрудника")
    @PostMapping("/update/{id}")
    public void update(@RequestParam UUID id, @RequestBody EmployeeDto updateEmployeeDto) {
        employeeService.updateEmployee(id, updateEmployeeDto);
    }

    @ApiOperation("Получение сотрудника по id")
    @GetMapping("/getById/{id}")
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







