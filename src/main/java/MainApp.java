import employeeCard.Employee;
import model.InMemoryEmployeeCardService;
import model.InMemoryPostService;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class MainApp {
    private static final Pattern employeesFilePattern = Pattern.compile(
                    "firstName: (?<firstName>.+)" +
                    "lastName: (?<lastName>.+)" +
                    "description: (?<description>.+|)" +
                    "characteristics: (?<characteristics>.+)" +
                    "postId: (?<postId>.+)");
    private static InMemoryEmployeeCardService inMemoryEmployeeCardService = new InMemoryEmployeeCardService();
    private static InMemoryPostService inMemoryPostService = new InMemoryPostService();

    public MainApp() {}

    public static void main(String[] args) throws Exception {
        final String PATH=args[0];
        if (PATH.isEmpty())
            throw new Exception("No PATH");
        inMemoryPostService.createPosts();
        readEmployeesFromFile(PATH);
        inMemoryEmployeeCardService.printSortedByFirstAndLastName(inMemoryEmployeeCardService.getEmployees());
    }

    public static void readEmployeesFromFile(String PATH) throws Exception {
        List<String> listEmployeesInfo = new ArrayList<>();

        Files.lines(Paths.get(PATH)).forEach(employee -> listEmployeesInfo.add(employee));
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

    private static Employee dataEmployee(String dataEmployee) throws Exception {
        Matcher m = employeesFilePattern.matcher(dataEmployee);
        if (!m.find())
            throw new Exception("File data is incorrect: " + dataEmployee);

        Employee employee = new Employee(
                m.group("firstName"),
                m.group("lastName"),
                m.group("description"),
                (Arrays.stream(m.group("characteristics").split(", ")).sorted()).collect(Collectors.toList()),
                inMemoryPostService.getPosts().get(UUID.fromString(m.group("postId"))));

        if ((employee.getFirstName().isEmpty()) || (employee.getLastName().isEmpty()) || employee.getCharacteristics().isEmpty() || employee.getPost() == null)
            throw new Exception("fill in the fields(the description may be empty)");
        return employee;
    }

}