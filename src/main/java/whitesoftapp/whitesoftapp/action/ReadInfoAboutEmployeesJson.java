package whitesoftapp.whitesoftapp.action;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import whitesoftapp.whitesoftapp.controller.EmployeeController;
import whitesoftapp.whitesoftapp.model.Employee;
import whitesoftapp.whitesoftapp.parser.ParsInfoEmployeeFromJson;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        listEmployees.forEach(employee -> employeeController.create(employee.getId(),parsInfoEmployeeFromJson.dataEmployee(employee)));
        return Files.lines(Paths.get(PATH)).collect(Collectors.toList());
    }

}
