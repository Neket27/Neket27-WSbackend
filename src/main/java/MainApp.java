import EmployeeCard.Employee;
import EmployeeCard.Post;
import EmployeeCard.controllers.NameAndLastNameController;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;


public class MainApp {
    public MainApp(){}

    public static void main(String[] args) throws IOException {
         Map<Long, Post> posts =new HashMap<>();
        List<Employee> employees = new ArrayList<>();
        final String PATH ="C:\\Users\\nikit\\Desktop\\info.txt";
        PrintStream p =System.out;

        Post post = new Post();
        p.println(post.getCountBlockPesonFromFile(PATH));


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

}
