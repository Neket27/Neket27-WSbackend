package EmployeeCard;

import lombok.Data;

import java.util.UUID;


@Data
public class Post  {
    UUID id;
    String name;

    public  Post(){}
    public Post(Employee employee) {
        this.name=employee.getFirstName();
    }

    public Post(String firstName) {
        this.name=firstName;
    }


    public Post(UUID id, String name) {
        this.id=id;
        this.name=name;
    }
}
