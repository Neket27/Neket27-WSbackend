package whitesoftapp.whitesoftapp.action.createEmployeesByInfoFromFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import whitesoftapp.whitesoftapp.controller.employee.EmployeeController;
import whitesoftapp.whitesoftapp.model.dtos.employee.CreateEmployeeDto;

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
    public List<String> readEmployeesFromFile(String PATH) throws IOException {
        List<CreateEmployeeDto> listEmployees = objectMapper.readValue(new File(PATH), new TypeReference<List<CreateEmployeeDto>>() {});
        listEmployees.forEach(employeeController::create);
        return Files.lines(Paths.get(PATH)).collect(Collectors.toList());
    }

}

