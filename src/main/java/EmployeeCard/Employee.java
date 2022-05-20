package EmployeeCard;

import lombok.Builder;
import lombok.Data;
import java.util.List;
@Data
public class Employee  {
    String firstName;
    String lastName;
    String description;
    List<String> characteristics;
    Post post;

    public Employee(){}
}


