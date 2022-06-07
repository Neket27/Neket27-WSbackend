package whitesoftapp.whitesoftapp.action;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import whitesoftapp.whitesoftapp.controller.EmployeeController;
import whitesoftapp.whitesoftapp.model.Employee;
import whitesoftapp.whitesoftapp.parser.ParsInfoEmployeeFromJson;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Data
@RequiredArgsConstructor
@Component
public class ReadInfoAboutEmployeesJson implements ReadEmployeesByInfoFromFile {

    private final EmployeeController employeeController;
    private final ParsInfoEmployeeFromJson parsInfoEmployeeFromJson;
    private final ObjectMapper objectMapper;

    public List<Employee> readEmployeesFromJson(String PATH) throws IOException {
        List<Employee> listEmployees = objectMapper.readValue(new File(PATH), new TypeReference<List<Employee>>() {});
        listEmployees.forEach(employee -> employeeController.create(employee.getId(),parsInfoEmployeeFromJson.dataEmployee(employee)));
        return listEmployees;
    }

}
