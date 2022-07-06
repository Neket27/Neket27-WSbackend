package whitesoftapp.controller.employee;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import whitesoftapp.action.CreateEmployeeArgumentAction;
import whitesoftapp.action.UpdateEmployeeArgumentAction;
import whitesoftapp.arguments.CreateEmployeeArgument;
import whitesoftapp.arguments.UpdateEmployeeArgument;
import whitesoftapp.controller.utils.mapper.employee.EmployeeMapper;
import whitesoftapp.model.dtos.employee.CreateEmployeeDto;
import whitesoftapp.model.dtos.employee.EmployeeDto;
import whitesoftapp.model.dtos.employee.UpdateEmployeeDto;
import whitesoftapp.service.employee.EmployeeService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final CreateEmployeeArgumentAction createEmployeeArgumentAction;
    private final UpdateEmployeeArgumentAction updateEmployeeArgumentAction;
    private final EmployeeMapper employeeMapper;

    @ApiOperation("Создание нового работника")
    @PostMapping("/create")
    public EmployeeDto create(@Valid @RequestBody CreateEmployeeDto createEmployeeDto) {
        CreateEmployeeArgument createEmployeeArgument = createEmployeeArgumentAction.create(createEmployeeDto);

        return employeeMapper.toDto(employeeService.create(createEmployeeArgument));
    }

    @ApiOperation("Обновление информации сотрудника")
    @PostMapping(value = "/update/{id}")
    public EmployeeDto update(@PathVariable UUID id, @RequestBody UpdateEmployeeDto updateEmployeeDto) {
        UpdateEmployeeArgument updateEmployeeArgumen=updateEmployeeArgumentAction.update(updateEmployeeDto);
        return employeeMapper.toDto(employeeService.update(id,updateEmployeeArgumen));
    }

    @ApiOperation("Получение сотрудника по id")
    @GetMapping("/{id}")
    public EmployeeDto getById(@PathVariable UUID id)  {

        return employeeMapper.toDto(employeeService.getById(id));
    }

    @ApiOperation("Вывод списка сотрудников")
    @GetMapping("/list")
    public HashMap<UUID, EmployeeDto> getList() {
        return employeeMapper.toListDto(employeeService.getList());
    }

    @ApiOperation("Удаление работника по id")
    @GetMapping("/remove/{id}")
    public void remove(@PathVariable UUID id) {
        employeeService.remove(id);
    }

}







