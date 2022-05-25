package service;

import employeeCard.Employee;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public interface EmployeeCardService {

    Employee get(UUID id);

    Employee get(String firstName, String lastName);

    void add(Employee employee);

    void add(List<Employee> employees);

    Comparator<? super Employee> comparatorInFirstNameAndLastName();

    void printSortedByFirstAndLastName(List<Employee> employees);


}
