import EmployeeCard.Employee;
import EmployeeCard.Post;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;


public class MainApp {
    private static Pattern regexpBlockEmployee = Pattern.compile("^[\\w,а-я,А-Я,:]*[\\ws].+");
    private static Pattern regexpFistName = Pattern.compile("^firstName:\\s[а-я,А-Я]*");
    private static Pattern regexpLastName=Pattern.compile("^lastName:\\s[а-я,А-Я]*");
    private static Pattern regexpDescription=Pattern.compile("^description:[a-z,A-Z,\\s,.,\\,?]*");
    private static Pattern regexpCharacteristics=Pattern.compile("^characteristics:[a-z,A-Z,\\s,.,]*\\s");
    private static Pattern regexpPostId=Pattern.compile("\\w*\\-\\w*\\-\\w*\\-\\w*\\-\\w*");
    private static Long indexEmployee= Long.valueOf(0);
    private static boolean flagDescription = false;
    private static boolean flagCharacteristics = false;
    private static Map<UUID, Post> posts = new HashMap<>();
    private static List<Employee> employees = new ArrayList<>();

    public MainApp() {
    }

    public static void main(String[] args) throws IOException {
        PrintStream p = System.out;
        Scanner enter = new Scanner(System.in);
        createPost(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"), "position at work_1");
        createPost(UUID.fromString("762d15a5-3bc9-43ef-ae96-02a680a557d0"), "position at work_2");
        createPost(UUID.fromString("606b99c0-b621-4f50-b0b6-58ed19ce6be1"), "position at work_3");
        createPost(UUID.fromString("762d15a5-3bc9-43ef-ae96-02a680a557d9"), "position at work_4");

        p.println("Enter path to file: ");
        String PATH= enter.nextLine();
        readFromFile(PATH);
        employees.sort(comparatorInFirstNameAndLastName());
        employees.forEach(employee -> p.println(employee));

    }




    public static void readFromFile(final String PATH) throws IOException {
        List<String> description = new ArrayList<>();
        List<String> characteristics = new ArrayList<>();
        if(PATH.isEmpty())
            throw new RuntimeException("Путь к файлу отсутствует.");

            // 2 стрима, так как при использовании они сами закравается, а мне нужно использовать в двух местах
            Stream<String> streamBlockPeson = Files.lines(Paths.get(PATH));
            Stream<String> streamBlockPeson2 = Files.lines(Paths.get(PATH));
            if(!(streamBlockPeson2.count()>0))
                throw new RemoteException("Файл пустой.");

            employees.add(new Employee());
            streamBlockPeson.forEach(
                    (line) -> {
                        Optional<String> optStringLine = Optional.ofNullable(getBlockEmployeeFromFile(line));
                        if (optStringLine.isPresent()) {
                            if (getNameFromFile(line) != null) {
                                line = getNameFromFile(line).replace("firstName: ", "");
                                employees.get(Math.toIntExact(indexEmployee)).setFirstName(line);
                            }
                            if (getLastNameFromFile(line) != null) {
                                line = getLastNameFromFile(line).replace("lastName: ", "");
                                employees.get(Math.toIntExact(indexEmployee)).setLastName(line);
                            }
                            if (getDescriptionFromFile(line) != null && flagDescription == false) {
                                flagDescription = true;
                            }
                            if (flagDescription == true && getCharacteristicsFromFile(line) == null) {
                                line = line.replace("description: ", "");
                                line = line.replace("description:", "");
                                description.add(line);
                            } else {
                                flagDescription = false;
                            }
                            if (getCharacteristicsFromFile(line) != null && flagCharacteristics == false) {
                                flagCharacteristics = true;
                            }
                            if (flagCharacteristics == true && getPostIdFromFile(line) == null) {
                                line = line.replace("characteristics: ", "");
                                Arrays.asList(line.split(",")).forEach(word -> characteristics.add(word));
                            } else {
                                flagCharacteristics = false;
                            }

                            if (getPostIdFromFile(line) != null) {
                                Employee person = employees.get(Math.toIntExact(indexEmployee));
                                line = getPostIdFromFile(line).replace("postId: ", "");
                                UUID id = UUID.fromString(line);
                                if(posts.get(id)!=null){
                                    Post post = posts.get(id);
                                    person.setPost(post);
                                }else
                                    throw new RuntimeException("Нет поста у работника c индексом "+indexEmployee);
                            }
                            employees.get(Math.toIntExact(indexEmployee)).setDescription(String.join(" ", description));
                            List<String> allCharacteristics = new ArrayList<>();
                            characteristics.forEach(value -> allCharacteristics.add(value));

                            if (!allCharacteristics.isEmpty())
                                Collections.sort(allCharacteristics);

                            employees.get(Math.toIntExact(indexEmployee)).setCharacteristics(allCharacteristics);

                        } else {
                            characteristics.clear();
                            indexEmployee++;
                            flagDescription = false;
                            flagCharacteristics = false;
                            description.clear();
                            employees.add(new Employee());
                        }

                    });
    }


    private static String getBlockEmployeeFromFile(String lineText) {
        Matcher match = regexpBlockEmployee.matcher(lineText);
        if (match.find())
            return match.group();
        return null;
    }
    public static String getNameFromFile(String lineText) {
        Matcher match = regexpFistName.matcher(lineText);
        if (match.find())
            return match.group();
        return null;
    }
    public static String getLastNameFromFile(String lineText) {
        Matcher match = regexpLastName.matcher(lineText);
        if (match.find())
            return match.group();
        return null;
    }
    private static String getDescriptionFromFile(String lineText) {
        Matcher match = regexpDescription.matcher(lineText);
        if (match.find())
            return match.group();
        return null;
    }
    private static String getCharacteristicsFromFile(String lineText) {
        Matcher match = regexpCharacteristics.matcher(lineText);
        if (match.find())
            return match.group();
        return null;
    }
    private static String getPostIdFromFile(String lineText) {
        Matcher match = regexpPostId.matcher(lineText);
        if (match.find())
            return match.group();
        return null;
    }

    private static void createPost(UUID uuid, String name) {
        posts.put(uuid, new Post(uuid, name));
    }
    private static void checkFillingFields(){
        final int[] index = {0};
    employees.forEach(
    employee -> {

        if (employee.getFirstName() == null)
            throw new RuntimeException("Employee with index " + index[0] + " No firstName.");
        if (employee.getLastName() == null)
            throw new RuntimeException("Employee with index " + index[0] + " No lastName.");
        if (employee.getCharacteristics().size() == 0)
            throw new RuntimeException("Employee with index " + index[0] + " No characteristics");
        if (employee.getPost() == null)
            throw new RuntimeException("No Post at employee with index " + index[0]);
        index[0]++;
        });
    }

    public static Comparator<? super Employee> comparatorInFirstNameAndLastName() {
        checkFillingFields();//сразу добавим проверку полей в компаратор, чтобы, если не окажется полей по которым идет сортировка, программа не упала в ошибку
        Comparator<Employee> employeeNameComparator
                = Comparator.comparing(Employee::getFirstName).thenComparing(Employee::getLastName);
        Collections.sort(employees, employeeNameComparator);
        return employeeNameComparator;
    }



}