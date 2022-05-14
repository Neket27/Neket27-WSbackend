package EmployeeCard;

import EmployeeCard.controllers.NameAndLastNameController;
import lombok.Data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@Data
public class Post extends Employee {
    Long id= Long.valueOf(-1);
    Long countPosts = Long.valueOf(0);
    String name;

    public  Post(){}
    public Post(Employee employee) {
        this.name=employee.getFirstName();
    }

    public Post(String firstName) {
        this.name=firstName;
    }


    public  List<Employee> getCountBlockPesonFromFile(String someText) throws IOException {
        final String PATH ="C:\\Users\\nikit\\Desktop\\info.txt";
        List<String> BlockPeson = new ArrayList<>();
        NameAndLastNameController nameAndLastNameController =new NameAndLastNameController();
        Stream<String> streamBlockPeson = Files.lines(Paths.get(someText));
        List<Employee> employees = new ArrayList<>();
        employees.add(Math.toIntExact(countPosts),new Employee());
        streamBlockPeson.forEach(
                (line) -> {
                    Optional<String> OptName = Optional.ofNullable(getBlock(line));

                    if (OptName.isPresent()) {
                      //  BlockPeson.add(getBlock(line));
                        System.out.println("id= "+Math.toIntExact(countPosts));
                        if(getName(line)!=null)
                        employees.get(Math.toIntExact(countPosts)).setFirstName(getName(line));
                        if(getLastName(line)!=null)
                            employees.get(Math.toIntExact(countPosts)).setLastName(getLastName(line));


                    }else {
                        countPosts=countPosts+1;
                       employees.add(new Employee());

                    }


                });


       // return BlockPeson;
        return employees;
    }
    public String getName(String someText) {
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

    public   String getLastName(String someText) {
        String val = null;
        Pattern regexp = Pattern.compile("^lastName:\\s[а-я,А-Я]*");

        Matcher match = regexp.matcher(someText);
        while (match.find()) {
            val=match.group();
        }
        //System.out.println("result "+val);
        return val;
    }



    private  String getBlock(String someText) {
        String val = null;
        Pattern regexp = Pattern.compile("^[\\w,а-я,А-Я,:]*[\\ws].+");
        Matcher match = regexp.matcher(someText);
        while (match.find()) {
            val = match.group();
        }
        return val;
    }
}
