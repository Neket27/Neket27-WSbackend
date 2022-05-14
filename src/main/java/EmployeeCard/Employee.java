package EmployeeCard;

import EmployeeCard.controllers.NameAndLastNameController;
import lombok.Data;

import java.io.IOException;
import java.util.List;

@Data
public class Employee {
    String firstName;
    String lastName;
    String description;
    List<String> characteristics;
    Post post;
    Long id;

    final String PATH ="C:\\Users\\nikit\\Desktop\\info.txt";
    public Employee(){}
    public Employee(Long id,NameAndLastNameController person) throws IOException {
        this.firstName=person.getNameFromFile(PATH);
        this.lastName=person.getLastNameFromFile(PATH);
    }
}
