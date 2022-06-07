package whitesoftapp.whitesoftapp.action;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import whitesoftapp.whitesoftapp.controller.EmployeeController;
import whitesoftapp.whitesoftapp.controller.PostController;
import whitesoftapp.whitesoftapp.model.dtos.EmployeeDto;
import whitesoftapp.whitesoftapp.notFoundException.EmployeeNotFoundException;
import whitesoftapp.whitesoftapp.parser.ParsInfoEmployeeFromTxt;
import whitesoftapp.whitesoftapp.repository.InMemoryEmployeeCard;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

@Data
@RequiredArgsConstructor
@Component
public class ReadInfoAboutEmployeesTxt implements ReadEmployeesByInfoFromFile {

    private final InMemoryEmployeeCard inMemoryEmployeeCard;
    private final PostController postController;
    private final EmployeeController employeeController;
    private final ParsInfoEmployeeFromTxt parsInfoEmployeeFromTxt;

    public List<String> readEmployeesFromFile(String PATH) throws Exception {
        List<String> listEmployeesInfo = new ArrayList<>();

        //  listEmployeesInfo.addAll((Collection<? extends String>) Files.lines(Paths.get(PATH))); Перестало работать
        Stream<String> streamInfoEmployees = Files.lines(Paths.get(PATH));  //а так работает
        streamInfoEmployees.forEach(employee -> listEmployeesInfo.add(employee));

        if (listEmployeesInfo.isEmpty())
            throw new EmployeeNotFoundException("The file is empty");

        List<String> listValueEmployee = new ArrayList<>();
        listEmployeesInfo.forEach(value -> {
            if (!value.isEmpty()) {
                listValueEmployee.add(value);
            } else {
                String infoOneEmployee = String.join("", listValueEmployee);
                listValueEmployee.clear();
                try {
                    EmployeeDto employee = parsInfoEmployeeFromTxt.dataEmployee(infoOneEmployee);
                    employeeController.create(employee.getId(),employee);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return listEmployeesInfo;
    }

}
