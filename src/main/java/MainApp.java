import EmployeeCard.Employee;
import EmployeeCard.Post;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;



public class MainApp {
    private static boolean flagDescription=false;
    private static boolean flagCharacteristics=false;
    private static Map<UUID, Post> posts =new HashMap<>();
    private static List<Employee> employees = new ArrayList<>();
   private static final String PATH = "C:\\Users\\nikit\\Desktop\\info.txt";

    public MainApp(){}

    public static void main(String[] args) throws IOException {
        PrintStream p = System.out;


    readFromFile();
    p.println(employees.get(2));
    }

    public static void readFromFile() throws IOException {
        List<String> description = new ArrayList<>();
        List<String> characteristics= new ArrayList<>();
        Stream<String> streamBlockPeson = Files.lines(Paths.get(PATH));
         long[] countPosts = {0};
        employees.add(Math.toIntExact(countPosts[0]), new Employee());
        streamBlockPeson.forEach(
                (line) -> {
                    Optional<String> OptStringLine = Optional.ofNullable(getBlockFromFile(line));

                    if (OptStringLine.isPresent()) {
                        //  BlockPeson.add(getBlock(line));
//                        System.out.println("id= " + Math.toIntExact(countPosts[0]));
                        if (getNameFromFile(line) != null) {
                            line=getNameFromFile(line).replace("firstName: ","");
                            employees.get(Math.toIntExact(countPosts[0])).setFirstName(line);
                        }
                        if (getLastNameFromFile(line) != null) {
                            line=getLastNameFromFile(line).replace("lastName: ","");
                            employees.get(Math.toIntExact(countPosts[0])).setLastName(line);
                        }
                        if (getDescriptionFromFile(line) != null && flagDescription ==false) {
                                    flagDescription =true;
                        }
                        if(flagDescription==true && getCharacteristicsFromFile(line)==null){
                            line= line.replace("description: ","");
                            description.add(line);
                        }else {
                            flagDescription=false;
                        }



                        if (getCharacteristicsFromFile(line) != null && flagCharacteristics ==false) {
                            flagCharacteristics =true;
                        }
                        if(flagCharacteristics==true && getPostIdFromFile(line)==null){
                           // line= line.replace("characteristics: ","");
//                            System.out.println("my_charact= "+line);
                            line= line.replace("characteristics: ","");
                            Arrays.asList(line.split(",")).forEach(word->characteristics.add(word));
                        //  System.out.println( "вывод = "+characteristics.get(0));
                          // System.out.println("h="+h);
                            //l.add(h);
                            //indexx=indexx+1;




                            //characteristics.add(line);
                        }else {
                            flagCharacteristics=false;
                        }
//                        if(!characteristics.isEmpty())
//                        System.out.println("AllCH= "+characteristics);

////////////////////////////////   /////////////////////   /////////////////////   ///////////////////////
//                        if (getCharacteristicsFromFile(line) != null && flagCharacteristics ==false) {
//                            flagCharacteristics =true;
//                        }
//                        List<String>combinedLinesCharacteristics=new ArrayList<>();
//                        if(flagCharacteristics==true && getPostIdFromFile(line)==null){
//                            combinedLinesCharacteristics= Arrays.asList(line.split("postId:.*"));
//                            if(!combinedLinesCharacteristics.isEmpty()) {
//                                combinedLinesCharacteristics= Collections.singletonList(combinedLinesCharacteristics.get(0).replace("characteristics:", ""));
//                                System.out.println("combinedLinesCharacteristics= " + combinedLinesCharacteristics.get(0));
//                            }
//                            if (!combinedLinesCharacteristics.isEmpty())
//                                characteristics.add(combinedLinesCharacteristics.get(0));
//                            else characteristics.add(null);
//
//                        }else {
//                            flagCharacteristics=false;
//                        }

//                        System.out.println("getPostIdFromFile= "+getNameFromFile(line));

                        if (getPostIdFromFile(line) != null) {
                            line=getPostIdFromFile(line).replace("postId: ","");
                            UUID id= UUID.fromString(line);
//                            System.out.println("UUID= "+id);
                            Post post = new Post();
                            posts.put(id,post);
                            employees.get(Math.toIntExact(countPosts[0])).setPost(post);

                        }



                    } else {
                        employees.get(Math.toIntExact(countPosts[0])).setDescription(String.join(" ",description));
                        List<String> allCharacteristics =new ArrayList<>();
                        characteristics.forEach(value->allCharacteristics.add(value));


//                        list.add(String.valueOf(characteristics));

//                        characteristics.clear();
//                        characteristics.add(list.get(0));
                       // if(characteristics.get(0))
//list.add("aaaaaaaaaa,dddddddddd,cccccccccc");
                        System.out.println("all= "+allCharacteristics);
                        if(!allCharacteristics.isEmpty())
                            System.out.println("allEmpty= "+!allCharacteristics.isEmpty());
                        Collections.sort(allCharacteristics);
                            employees.get(Math.toIntExact(countPosts[0])).setCharacteristics(allCharacteristics);

                        characteristics.clear();
                       // if(!characteristics.isEmpty())

//                        if(!characteristics.isEmpty())
//                            characteristics.remove(characteristics.size()-1);
                            //Collections.sort(characteristics);
//                        List<String> allCharacteristics=new ArrayList<>();
//                        int indexx = 0;
//                        while(indexx<characteristics.size()) {
//                            //System.out.println("size= "+characteristics.size());
//                        Arrays.asList(characteristics.get(indexx).split(",")).forEach(word->allCharacteristics.add(word));
//                          //System.out.println( "вывод = "+);
//                          // System.out.println("h="+h);
//                            //l.add(h);
//                            indexx=indexx+1;
//                        }
//                            indexx=0;

                        //Collections.sort(allCharacteristics);
                       // l.forEach(word->System.out.println(word));
                      // System.out.println("ll= "+allCharacteristics);
//                        Collections.sort(characteristics, (a,b) -> a.compareToIgnoreCase(b));
                      //  System.out.println("characteristicsQ= "+characteristics.get(0));

                        countPosts[0] = countPosts[0] + 1;
                        flagDescription=false;
                        flagCharacteristics=false;
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
                    valresult=val;
            }
            //System.out.println("valresult= "+valresult);
            return valresult;
        }

        public static String getLastNameFromFile(String lineText) {
            String val = null;
            Pattern regexp = Pattern.compile("^lastName:\\s[а-я,А-Я]*");

            Matcher match = regexp.matcher(lineText);
            while (match.find()) {
                val=match.group();
            }
            //System.out.println("result "+val);
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
//        System.out.println("description= "+val);
    }
    return val;
}

    private static String getCharacteristicsFromFile(String lineText) {
        String val = null;
        Pattern regexp = Pattern.compile("^characteristics:[a-z,A-Z,\\s,.,]*\\s");
        Matcher match = regexp.matcher(lineText);
        while (match.find()) {
            val = match.group();
//        System.out.println("description= "+val);
        }
        return val;
    }

    private static String getPostIdFromFile(String lineText) {
        String val = null;
        Pattern regexp = Pattern.compile("\\w*\\-\\w*\\-\\w*\\-\\w*\\-\\w*");
        Matcher match = regexp.matcher(lineText);
        while (match.find()) {
            val = match.group();
//        System.out.println("ID888888888888888= "+val);
        }
        return val;
    }




//         Map<Long, Post> posts =new HashMap<>();
//        List<Employee> employees = new ArrayList<>();
//        final String PATH ="C:\\Users\\nikit\\Desktop\\info.txt";
//        PrintStream p =System.out;
//
//        Post post = new Post();
//        p.println(post.getCountBlockPesonFromFile(PATH));









        //p.println(post.getCountBlockPesonFromFile(PATH));
//        Long cPost = post.getCountBlockPesonFromFile(PATH);
//        for(int i=0;i<(cPost);i++){
//
//                employees.add(new Employee());
//            //  posts.put((long) i,new Post(new Employee((long)i,new NameAndLastNameController()).getFirstName()));
//            p.println(i);
//        }
//        employees.get(2).setFirstName("Nikita");
//
//
//        p.println(employees.get(2));













//        NameAndLastNameController ob= new NameAndLastNameController();
//        final String PATH ="C:\\Users\\nikit\\Desktop\\info.txt";
//      //System.out.println(ob.getNameFromFile(PATH));
//      //System.out.println(ob.getLastNameFromFile(PATH));
//
//        Employee employee1 = new Employee(ob);
//        Post post1 = new Post(employee1);
//        System.out.println(employee1.getFirstName());
//        System.out.println(employee1.getLastName());
//
//    System.out.println(post1.getBlockPesonFromFile(PATH));
//    System.out.println(post1.getId());


}
