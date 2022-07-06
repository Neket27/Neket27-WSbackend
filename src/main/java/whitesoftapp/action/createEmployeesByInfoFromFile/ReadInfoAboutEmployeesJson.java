package whitesoftapp.action.createEmployeesByInfoFromFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import whitesoftapp.controller.employee.EmployeeController;
import whitesoftapp.model.dtos.employee.CreateEmployeeDto;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Data
@RequiredArgsConstructor
@Primary
@Component
public class ReadInfoAboutEmployeesJson implements ReadEmployeesByInfoFromFile {

    private final EmployeeController employeeController;
    private final ObjectMapper objectMapper;

    @Override
    public List<String> readEmployeesFromFile(String PATH) throws Exception {
        List<CreateEmployeeDto> listEmployees = objectMapper.readValue(new File(PATH), new TypeReference<List<CreateEmployeeDto>>() {});
        listEmployees.forEach(employeeController::create);

        return Files.lines(Paths.get(PATH)).collect(Collectors.toList());
    }

}

