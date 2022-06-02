package action;

import controller.EmployeeController;
import controller.PostController;
import model.Employee;
import model.Post;
import model.dtos.CreateEmployeeDto;
import model.dtos.DtoEntity;
import repository.InMemoryPost;
import service.CreateCardsEmployeesByInfoFromFileService;
import service.EmployeeCardService;
import service.PostService11;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import repository.InMemoryEmployeeCard;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Component;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Data
@RequiredArgsConstructor
@Component
public class CreateCardsEmployeesByInfoFromFile implements CreateCardsEmployeesByInfoFromFileService {

    private final Pattern employeesFilePattern = Pattern.compile(
                    "firstName: (?<firstName>.+)" +
                    "lastName: (?<lastName>.+)" +
                    "description: (?<description>.+|)" +
                    "characteristics: (?<characteristics>.+)" +
                    "postId: (?<postId>.+)");

    private final EmployeeCardService employeeCardService;
    private final PostService11 postService;
    private final PostController postController;
    private final EmployeeController employeeController;



    @Override
    public void createPosts() {
        postService.createPosts();
    }

    @Override
    public void printSortedByFirstAndLastName() {
        employeeCardService.printSortedByFirstAndLastName();
    }

    @Override
    public InMemoryPost getPosts() {
        return (InMemoryPost) postService;
    }

    @Override
    public InMemoryEmployeeCard getEmployees() {
        return (InMemoryEmployeeCard) employeeCardService;
    }

    @Override
    public void readEmployeesFromFile(String PATH) throws Exception {
        List<String> listEmployeesInfo = new ArrayList<>();
        listEmployeesInfo.addAll((Collection<? extends String>) Files.lines(Paths.get(PATH)));
        if (listEmployeesInfo.isEmpty())
            throw new Exception("The file is empty");

        List<String> listValueEmployee = new ArrayList<>();
        listEmployeesInfo.forEach(value -> {
            if (!value.isEmpty()) {
                listValueEmployee.add(value);
            } else {
                String infoOneEmployee = String.join("", listValueEmployee);
                listValueEmployee.clear();
                try {
                    employeeCardService.add(dataEmployee(infoOneEmployee));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private Employee dataEmployee(String dataEmployee) throws Exception {
        Matcher m = employeesFilePattern.matcher(dataEmployee);
        if (!m.find())
            throw new Exception("File data is incorrect: " + dataEmployee);

        List<String> characteristics = (Arrays.stream(m.group("characteristics").split(", ")).sorted()).collect(Collectors.toList());
        if ((m.group("firstName").isEmpty()) || (m.group("lastName").isEmpty()) || characteristics.isEmpty() || postService.get(UUID.fromString(m.group("postId"))) == null)
            throw new Exception("fill in the fields(the description may be empty)");

        Employee employee = new Employee(
                m.group("firstName"),
                m.group("lastName"),
                m.group("description"),
                characteristics,
                postService.get(UUID.fromString(m.group("postId"))));

        return employee;
    }

    @Override
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
        jsonArray.forEach(employee ->  employeeController.create(dataEmployee((JSONObject) employee)));
//                employeeCardService.add(dataEmployee((JSONObject) employee)));
        return jsonArray;
    }

    private CreateEmployeeDto dataEmployee(JSONObject employee) {
        List<String> characteristics = new ArrayList<>((JSONArray) employee.get("characteristics"));
        Post post = postService.get(UUID.fromString((String) employee.get("postId")));
        return CreateEmployeeDto.builder()
                .firstName(employee.get("firstName").toString())
                .lastName(employee.get("lastName").toString())
                .description(employee.get("description").toString())
                .characteristics(characteristics)
                .post(post)
                .build();
    }

}
