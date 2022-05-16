import EmployeeCard.Employee;
import EmployeeCard.Post;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;



public class MainApp {
    private static boolean flagDescription = false;
    private static boolean flagCharacteristics = false;
    private static Map<UUID, Post> posts = new HashMap<>();
    private static List<Employee> employees = new ArrayList<>();
    private static final String PATH = "C:\\Users\\nikit\\Desktop\\info.txt";

    public MainApp() {
    }

    public static void main(String[] args) throws IOException {
        PrintStream p = System.out;


        readFromFile();
        employees.forEach(employee -> p.println(employee));
        Comparator<Employee> comparator = new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                if(o1.getLastName().compareTo(o2.getLastName())<0)
                {
                    return -1;
                }
                else if(o1.getLastName().compareTo(o2.getLastName())>0){
                    return 1;
                }

                return 0;
                //здесь вместо return 0; пишем еще условие сортировки по FirstName
            }

            @Override
            public boolean equals(Object obj) {
                return false;
            }
        };
        employees.sort(comparator);
        p.println(employees);


        }

            public static void readFromFile() throws IOException {
        List<String> description = new ArrayList<>();
        List<String> characteristics = new ArrayList<>();
        Stream<String> streamBlockPeson = Files.lines(Paths.get(PATH));
        long[] countPosts = {0};
        employees.add(Math.toIntExact(countPosts[0]), new Employee());
        streamBlockPeson.forEach(
                (line) -> {
                    Optional<String> OptStringLine = Optional.ofNullable(getBlockFromFile(line));

                    if (OptStringLine.isPresent()) {

                        if (getNameFromFile(line) != null) {
                            line = getNameFromFile(line).replace("firstName: ", "");
                            employees.get(Math.toIntExact(countPosts[0])).setFirstName(line);
                        }
                        if (getLastNameFromFile(line) != null) {
                            line = getLastNameFromFile(line).replace("lastName: ", "");
                            employees.get(Math.toIntExact(countPosts[0])).setLastName(line);
                        }
                        if (getDescriptionFromFile(line) != null && flagDescription == false) {
                            flagDescription = true;
                        }
                        if (flagDescription == true && getCharacteristicsFromFile(line) == null) {
                            line = line.replace("description: ", "");
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
                            Employee person = employees.get(Math.toIntExact(countPosts[0]));
                            line = getPostIdFromFile(line).replace("postId: ", "");
                            UUID id = UUID.fromString(line);
                            String name = person.getFirstName();
                            Post post = new Post(id,name);
                            post.setId(id);
                            posts.put(id, post);
                            System.out.println("namePost= "+name);
                            person.setPost(post);
                        }
                    } else {
                        employees.get(Math.toIntExact(countPosts[0])).setDescription(String.join(" ", description));
                        List<String> allCharacteristics = new ArrayList<>();
                        characteristics.forEach(value -> allCharacteristics.add(value));

                        // System.out.println("all= "+allCharacteristics);
                        if (!allCharacteristics.isEmpty())
                            //  System.out.println("allEmpty= "+!allCharacteristics.isEmpty());
                            Collections.sort(allCharacteristics);
                        employees.get(Math.toIntExact(countPosts[0])).setCharacteristics(allCharacteristics);
                        characteristics.clear();
                        countPosts[0] = countPosts[0] + 1;
                        flagDescription = false;
                        flagCharacteristics = false;
                        description.clear();
                        employees.add(new Employee());
                    }
                });
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

    private static String getBlockFromFile(String lineText) {
        String val = null;
        Pattern regexp = Pattern.compile("^[\\w,а-я,А-Я,:]*[\\ws].+");
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