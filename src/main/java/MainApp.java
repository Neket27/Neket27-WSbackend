import EmployeeCard.Employee;
import Model.InMemoryEmployeeCardService;
import Model.InMemoryPostService;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainApp {
    private static final Pattern employeesFilePattern = Pattern.compile(
                    "firstName: (?<firstName>.+)" +
                    "lastName: (?<lastName>.+)" +
                    "description: (?<description>.+|)" +
                    "characteristics: (?<characteristics>.+)" +
                    "postId: (?<postId>.+)");
    private static InMemoryEmployeeCardService inMemoryEmployeeCardService =new InMemoryEmployeeCardService();
    private static InMemoryPostService inMemoryPostService =new InMemoryPostService();
    public MainApp() {}

    public static void main(String[] args) throws Exception {
        final String PATH=args[0];
        if (PATH.isEmpty())
            throw new Exception("No PATH");
        inMemoryPostService.cretePost();
        readEmployeesFromFile(PATH);
        print(inMemoryEmployeeCardService.getEmployees());

    }

    public static void readEmployeesFromFile(String PATH) throws Exception {
        List<String> listEmployeesInfo = new ArrayList<>();
        Stream<String> streamInfoEmployees = Files.lines(Paths.get(PATH));
        Stream<String> streamBlockPeson2 = Files.lines(Paths.get(PATH));
        if(!(streamBlockPeson2.count()>0))
            throw new Exception("The file is empty");

        streamInfoEmployees.forEach(employee->listEmployeesInfo.add(employee));
        List<String>listValueEmployee= new ArrayList<>();
        listEmployeesInfo.forEach(value->{
            if(!value.isEmpty())
                listValueEmployee.add(value);
            else {
                String infoOneEmployee=String.join("", listValueEmployee);
                listValueEmployee.clear();
                try {inMemoryEmployeeCardService.getEmployees().add(dataEmploe(infoOneEmployee));}
                catch (Exception e) {e.printStackTrace();}
            }
        });
    }

    private static Employee dataEmploe(String employee) throws Exception {
        Matcher m = employeesFilePattern.matcher(employee);
        if(!m.find())
            throw new Exception("Incorrect file: " + employee);

        Employee emoloyee=new Employee(
                m.group("firstName"),
                m.group("lastName"),
                m.group("description"),
                (Arrays.stream(m.group("characteristics").split(", ")).sorted()).collect(Collectors.toList()),
                inMemoryPostService.getPosts().get(UUID.fromString(m.group("postId"))));

        if ((emoloyee.getFirstName().isEmpty()) || (emoloyee.getLastName().isEmpty()) || emoloyee.getCharacteristics().isEmpty()||emoloyee.getPost()!=null )
            throw new Exception("fill in the fields(the description may be empty)");
        return emoloyee;
    }

    private static void print(List<Employee> employees) {
        employees.sort(comparatorInFirstNameAndLastName());
        employees.forEach(System.out::println);
    }

    public static Comparator<? super Employee> comparatorInFirstNameAndLastName() {
        Comparator<Employee> employeeNameComparator
                = Comparator.comparing(Employee::getFirstName).thenComparing(Employee::getLastName);
        Collections.sort(inMemoryEmployeeCardService.getEmployees(), employeeNameComparator);
        return employeeNameComparator;
    }

}