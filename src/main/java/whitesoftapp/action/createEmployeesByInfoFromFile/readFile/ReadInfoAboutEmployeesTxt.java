package whitesoftapp.action.createEmployeesByInfoFromFile.readFile;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import whitesoftapp.action.createEmployeesByInfoFromFile.readFile.parser.ParsInfoEmployeeFromTxt;
import whitesoftapp.controller.employee.EmployeeController;
import whitesoftapp.controller.post.PostController;
import whitesoftapp.model.dtos.employee.CreateEmployeeDto;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@RequiredArgsConstructor
@Component
public class ReadInfoAboutEmployeesTxt implements ReadEmployeesByInfoFromFile {

    private final PostController postController;
    private final EmployeeController employeeController;
    private final ParsInfoEmployeeFromTxt parsInfoEmployeeFromTxt;

    @Override
    public List<String> readEmployeesFromFile(String path) throws Exception {
        List<String> listEmployeesInfo = new ArrayList<>();
        listEmployeesInfo.addAll(Files.lines(Paths.get(path)).collect(Collectors.toList()));

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
                    CreateEmployeeDto createEmployeeDto = parsInfoEmployeeFromTxt.dataEmployee(infoOneEmployee);
                    employeeController.create(createEmployeeDto);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return listEmployeesInfo;
    }

}
