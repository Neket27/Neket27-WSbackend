import com.sun.xml.internal.ws.util.StringUtils;

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


public class MainApp {
    public MainApp(){}

    public static void main(String[] args) throws IOException {

        //Map<UUID, Post> posts;

        Stream<String> streamFromFiles = Files.lines(Paths.get("C:\\Users\\nikit\\Desktop\\info.txt"));
        //streamFromFiles.forEach((String line)->System.out.println(line));


        //String text = "firstName: Егор Алла Александр";
        //String text = String.valueOf(streamFromFiles);

        List<String> ListName = new ArrayList<>();
        List<String> ListLastName = new ArrayList<>();
        streamFromFiles.forEach(
                (line) ->{
                    Optional<String> OptName = Optional.ofNullable(getNameFromFile(line));
                    Optional<String> OptLastName = Optional.ofNullable(getLastNameFromFile(line));
                    if(OptName.isPresent())
                        ListName.add(getNameFromFile(line));

                    if(OptLastName.isPresent())
                        ListLastName.add(getLastNameFromFile(line));
                });


        System.out.println(ListName.get(0));
        System.out.println(ListLastName.get(0));
    }

    static String getNameFromFile(String someText){
        String val = null;
        Pattern regexp = Pattern.compile("^firstName:\\s[а-я,А-Я]*");

        Matcher match = regexp.matcher(someText);
        while (match.find()) {
            val=match.group();
        }
        return val;
    }


    static String getLastNameFromFile(String someText){
        String val = null;
        Pattern regexp = Pattern.compile("^lastName:\\s[а-я,А-Я]*");

        Matcher match = regexp.matcher(someText);
        while (match.find()) {
            val=match.group();
        }
        return val;
    }

}
