package whitesoftapp.whitesoftapp.action.createEmployeesByInfoFromFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import whitesoftapp.whitesoftapp.controller.EmployeeController;
import whitesoftapp.whitesoftapp.model.Employee;
import whitesoftapp.whitesoftapp.action.createEmployeesByInfoFromFile.parser.ParsInfoEmployeeFromJson;

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
    private final ParsInfoEmployeeFromJson parsInfoEmployeeFromJson;
    private final ObjectMapper objectMapper;

    @Override
    public List<String> readEmployeesFromFile(String PATH) throws IOException {
        List<Employee> listEmployees = objectMapper.readValue(new File(PATH), new TypeReference<List<Employee>>() {});
        listEmployees.forEach(employee -> employeeController.create(parsInfoEmployeeFromJson.dataEmployee(employee)));
        return Files.lines(Paths.get(PATH)).collect(Collectors.toList());
    }

}
