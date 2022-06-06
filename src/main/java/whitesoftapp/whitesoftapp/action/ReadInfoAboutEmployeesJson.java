package whitesoftapp.whitesoftapp.action;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Component;
import whitesoftapp.whitesoftapp.controller.EmployeeController;
import whitesoftapp.whitesoftapp.parser.ParsInfoEmployeeFromJson;
import java.io.File;
import java.io.FileReader;

@Data
@RequiredArgsConstructor
@Component
public class ReadInfoAboutEmployeesJson implements ReadEmployeesByInfoFromFile {

    private final EmployeeController employeeController;
    private final ParsInfoEmployeeFromJson parsInfoEmployeeFromJson;

    public JSONArray readEmployeesFromJson(String PATH) {
        File file = new File(PATH);
        JSONArray jsonArray = null;
        try (FileReader fileReader = new FileReader(file)) {
            if (fileReader == null)
                throw new Exception("The file is empty");

            JSONParser parser = new JSONParser();
            jsonArray = (JSONArray) parser.parse(fileReader);
        } catch (Exception e) {
            e.printStackTrace();
        }
        jsonArray.forEach(employee -> employeeController.create(parsInfoEmployeeFromJson.dataEmployee((JSONObject) employee)));
        return jsonArray;
    }

}
