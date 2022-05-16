import EmployeeCard.Employee;
import EmployeeCard.Post;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;


public class MainApp {
    private static Long indexEmployee= Long.valueOf(0);
    private static boolean flagDescription = false;
    private static boolean flagCharacteristics = false;
    private static Map<UUID, Post> posts = new HashMap<>();
    private static List<Employee> employees = new ArrayList<>();
    private static String PATH = null;
    public MainApp() {
    }

    public static void main(String[] args) throws IOException {
        PrintStream p = System.out;
        Scanner enter = new Scanner(System.in);
        p.println("Enter path to file: ");
        PATH= enter.nextLine();


        readFromFile();
        employees.sort(comparatorInFirstNameAndLastName());
        employees.forEach(employee -> p.println(employee));
        p.println(" ");
        employees.forEach(
                employee -> {
                    p.print ("FirstName: ");
                    p.println(employee.getFirstName());
                    p.print("Post job: ");
                    p.println(employee.getPost().getName());
                    p.print("Description: ");
                    p.println(employee.getDescription());
                    p.print("Characteristics:");
                    employee.getCharacteristics().forEach(characteristic-> p.print(characteristic+" "));
                    p.println(" ");p.println(" ");

                });

    }




    private static Comparator<Employee> comparatorInFirstNameAndLastName() {
         Comparator<Employee> comparatorInFirstNameAndLastName = new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                if (o1.getLastName().compareTo(o2.getLastName()) < 0) {
                    return -1;
                } else if (o1.getLastName().compareTo(o2.getLastName()) > 0) {
                    return 1;
                }

                if (o1.getFirstName().compareTo(o2.getFirstName()) < 0)
                    return -1;
                else if (o1.getFirstName().compareTo(o2.getLastName()) > 0)
                    return 1;

                return 0;

            }

//            @Override
//            public boolean equals(Object obj) {
//                return false;
//            }
        };
        return comparatorInFirstNameAndLastName;
    }

            public static void readFromFile() throws IOException {
        List<String> description = new ArrayList<>();
        List<String> characteristics = new ArrayList<>();
        Stream<String> streamBlockPeson = Files.lines(Paths.get(PATH));

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
                            String name = person.getFirstName();
                            Post post = new Post(id,name);
                            post.setId(id);
                            posts.put(id, post);
                            person.setPost(post);
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
        String val = null;
        Pattern regexp = Pattern.compile("^[\\w,а-я,А-Я,:]*[\\ws].+");
        Matcher match = regexp.matcher(lineText);
        while (match.find()) {
            val = match.group();
        }
        return val;
    }

    public static String getNameFromFile(String someText) {
        String val = null;
        String valresult = null;
        Pattern regexp = Pattern.compile("^firstName:\\s[а-я,А-Я]*");

        Matcher match = regexp.matcher(someText);
        while (match.find()) {
            val = match.group();
            Optional<String> OptName = Optional.ofNullable(val);
            if (OptName.isPresent())
                valresult = val;
        }
        return valresult;
    }

    public static String getLastNameFromFile(String lineText) {
        String val = null;
        Pattern regexp = Pattern.compile("^lastName:\\s[а-я,А-Я]*");

        Matcher match = regexp.matcher(lineText);
        while (match.find()) {
            val = match.group();
        }
        return val;
    }

    private static String getDescriptionFromFile(String lineText) {
        String val = null;
        Pattern regexp = Pattern.compile("^description:[a-z,A-Z,\\s,.,\\,?]*");
        Matcher match = regexp.matcher(lineText);
        while (match.find()) {
            val = match.group();
        }
        return val;
    }

    private static String getCharacteristicsFromFile(String lineText) {
        String val = null;
        Pattern regexp = Pattern.compile("^characteristics:[a-z,A-Z,\\s,.,]*\\s");
        Matcher match = regexp.matcher(lineText);
        while (match.find()) {
            val = match.group();
        }
        return val;
    }

    private static String getPostIdFromFile(String lineText) {
        String val = null;
        Pattern regexp = Pattern.compile("\\w*\\-\\w*\\-\\w*\\-\\w*\\-\\w*");
        Matcher match = regexp.matcher(lineText);
        while (match.find()) {
            val = match.group();
        }
        return val;
    }


}