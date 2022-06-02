package service;

import model.Employee;
import model.Post;
import model.dtos.DtoEntity;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
public interface EmployeeCardService {

    Employee get(UUID id);

    Employee get(String firstName, String lastName);

    void add(Employee employee);

    void add(List<Employee> employees);

    Comparator<? super Employee> comparatorInFirstNameAndLastName();

    void printSortedByFirstAndLastName();

    void print();

}
