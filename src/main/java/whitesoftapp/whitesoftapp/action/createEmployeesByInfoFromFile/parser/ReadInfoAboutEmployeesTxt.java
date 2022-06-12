package whitesoftapp.whitesoftapp.action.createEmployeesByInfoFromFile.parser;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import whitesoftapp.whitesoftapp.action.createEmployeesByInfoFromFile.ParsInfoEmployeeFromTxt;
import whitesoftapp.whitesoftapp.action.createEmployeesByInfoFromFile.ReadEmployeesByInfoFromFile;
import whitesoftapp.whitesoftapp.controller.EmployeeController;
import whitesoftapp.whitesoftapp.controller.PostController;
import whitesoftapp.whitesoftapp.model.dtos.employee.EmployeeDto;
import whitesoftapp.whitesoftapp.repository.InMemoryEmployeeCard;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@RequiredArgsConstructor
@Component
public class ReadInfoAboutEmployeesTxt implements ReadEmployeesByInfoFromFile {

    private final InMemoryEmployeeCard inMemoryEmployeeCard;
    private final PostController postController;
    private final EmployeeController employeeController;
    private final ParsInfoEmployeeFromTxt parsInfoEmployeeFromTxt;

    @Override
    public List<String> readEmployeesFromFile(String PATH) throws Exception {
        List<String> listEmployeesInfo = new ArrayList<>();
        listEmployeesInfo.addAll(Files.lines(Paths.get(PATH)).collect(Collectors.toList()));

        if (listEmployeesInfo.isEmpty()) {
            throw new Exception("The file is empty");
        }

        List<String> listValueEmployee = new ArrayList<>();
        listEmployeesInfo.forEach(value -> {
            if (!value.isEmpty()) {
                listValueEmployee.add(value);
            } else {
                String infoOneEmployee = String.join("", listValueEmployee);
                listValueEmployee.clear();
                try {
                    EmployeeDto employee = parsInfoEmployeeFromTxt.dataEmployee(infoOneEmployee);
                    employeeController.create(employee.getId(), employee);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return listEmployeesInfo;
    }

}
