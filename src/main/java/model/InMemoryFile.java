package model;

import employeeCard.Employee;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import service.FileService;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class InMemoryFile implements FileService {

    private final Pattern employeesFilePattern = Pattern.compile(
                    "firstName: (?<firstName>.+)" +
                    "lastName: (?<lastName>.+)" +
                    "description: (?<description>.+|)" +
                    "characteristics: (?<characteristics>.+)" +
                    "postId: (?<postId>.+)");

    private InMemoryPostService inMemoryPostService = new InMemoryPostService();
    private InMemoryEmployeeCardService inMemoryEmployeeCardService = new InMemoryEmployeeCardService();

    @Override
    public InMemoryPostService getPosts() {
        return inMemoryPostService;
    }

    @Override
    public InMemoryEmployeeCardService getEmployees() {
        return inMemoryEmployeeCardService;
    }

    public void readEmployeesFromFile(String PATH) throws Exception {
        List<String> listEmployeesInfo = new ArrayList<>();
        listEmployeesInfo.addAll((Collection<? extends String>) Files.lines(Paths.get(PATH)));
        List<String> listValueEmployee = new ArrayList<>();
        listEmployeesInfo.forEach(value -> {
            if (!value.isEmpty()) {
                listValueEmployee.add(value);
            } else {
                String infoOneEmployee = String.join("", listValueEmployee);
                listValueEmployee.clear();
                try {
                    inMemoryEmployeeCardService.add(dataEmployee(infoOneEmployee));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        if (listEmployeesInfo.isEmpty())
            throw new Exception("The file is empty");
    }

    private Employee dataEmployee(String dataEmployee) throws Exception {
        Matcher m = employeesFilePattern.matcher(dataEmployee);
        if (!m.find())
            throw new Exception("File data is incorrect: " + dataEmployee);

        Employee employee = new Employee(
                m.group("firstName"),
                m.group("lastName"),
                m.group("description"),
                (Arrays.stream(m.group("characteristics").split(", ")).sorted()).collect(Collectors.toList()),
                inMemoryPostService.get(UUID.fromString(m.group("postId"))));

        if ((employee.getFirstName().isEmpty()) || (employee.getLastName().isEmpty()) || employee.getCharacteristics().isEmpty() || employee.getPost() == null)
            throw new Exception("fill in the fields(the description may be empty)");
        return employee;
    }

    @Override
    public JSONArray readEmployeesFromJson(String PATH) {
        File file = new File(PATH);
        JSONArray jsonArray = null;
        try (FileReader fileReader = new FileReader(file)) {
            JSONParser parser = new JSONParser();
            jsonArray = (JSONArray) parser.parse(fileReader);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        jsonArray.forEach(employee -> inMemoryEmployeeCardService.add(dataEmployee((JSONObject) employee)));
        return jsonArray;
    }

    private Employee dataEmployee(JSONObject employee) {
        return Employee.builder()
                .firstName(employee.get("firstName").toString())
                .lastName(employee.get("lastName").toString())
                .description(employee.get("description").toString())
                .characteristics(new ArrayList<>((JSONArray) employee.get("characteristics")))
                .post(inMemoryPostService.get((UUID.fromString((String) employee.get("postId")))))
                .build();
    }

}
