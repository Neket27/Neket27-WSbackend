package EmployeeCard;
import lombok.Data;

import java.io.IOException;
import java.util.List;

@Data
public class Employee  {
    String firstName;
    String lastName;
    String description;
    List<String> characteristics;
    Post post;
   // Long id;

    final String PATH ="C:\\Users\\nikit\\Desktop\\info.txt";
    public Employee(){}



}
