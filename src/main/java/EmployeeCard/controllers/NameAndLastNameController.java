package EmployeeCard.controllers;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;


public class NameAndLastNameController  {
    private static   NameAndLastNameController person ;


    //streamFromFiles.forEach((String line)->System.out.println(line));


    //String text = "firstName: Егор Алла Александр";
    //String text = String.valueOf(streamFromFiles);





    // System.out.println(ListName.get(0));
    // System.out.println(ListLastName.get(0));

    //Stream<String> streamFromFiles = Files.lines(Paths.get("C:\\Users\\nikit\\Desktop\\info.txt"));

     public String getNameFromFile(String someText) throws IOException {

        List<String> ListName = new ArrayList<>();
        AtomicReference<String> val3 = new AtomicReference<>();
        Stream<String> streamFromFiles = Files.lines(Paths.get(someText));
        streamFromFiles.forEach(
                (line) -> {
                    Optional<String> OptName = Optional.ofNullable(getName(line));

                    if (OptName.isPresent())
                        val3.set(getName(line));

                });

           // System.out.println("name,lastname_id= "+id);
        return val3.get();
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
        return valresult;
    }


     public String getLastNameFromFile(String someText) throws IOException {
         List<String> ListLastName = new ArrayList<>();
        Stream<String> streamFromFiles = Files.lines(Paths.get(someText));
        streamFromFiles.forEach(
                (line) -> {

                    Optional<String> OptLastName = Optional.ofNullable(getLastName(line));
                    if (OptLastName.isPresent())
                        ListLastName.add(getLastName(line));
                });

        return ListLastName.get(0);

     }


    public   String getLastName(String someText) {
        String val = null;
        Pattern regexp = Pattern.compile("^lastName:\\s[а-я,А-Я]*");

        Matcher match = regexp.matcher(someText);
        while (match.find()) {
            val=match.group();
        }
        return val;
    }


}