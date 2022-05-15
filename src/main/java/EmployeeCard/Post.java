package EmployeeCard;

import lombok.Data;

import java.util.UUID;



public class Post extends Employee {
    UUID id;
    Long countPosts = Long.valueOf(0);
    String name;

    public  Post(){}
    public Post(Employee employee) {
        this.name=employee.getFirstName();
    }

    public Post(String firstName) {
        this.name=firstName;
    }



}
