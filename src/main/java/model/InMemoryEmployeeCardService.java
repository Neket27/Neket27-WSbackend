package model;

import employeeCard.Employee;
import service.EmployeeCardService;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.*;

@Data
@RequiredArgsConstructor
public class InMemoryEmployeeCardService implements EmployeeCardService {

    private List<Employee> employees = new ArrayList<>();

    @Override
    public Employee get(UUID id) {
        return this.employees.stream()
                .filter(employee -> (employee.getPost().getId()).equals(id)).findFirst().get();
    }

    @Override
    public Employee get(String firstName, String lastName) {
        return this.employees.stream()
                .filter(employee -> (employee.getFirstName().equals(firstName)) && (employee.getLastName().equals(lastName))).findFirst().get();
    }

    @Override
    public void add(Employee employee) {
        this.employees.add(employee);
    }

    @Override
    public void add(List<Employee> employees) {
        this.employees.addAll(employees);
    }

    @Override
    public Comparator<? super Employee> comparatorInFirstNameAndLastName() {
        return Comparator.comparing(Employee::getFirstName).thenComparing(Employee::getLastName);
    }

    @Override
    public void printSortedByFirstAndLastName() {
        this.employees.sort(comparatorInFirstNameAndLastName());
        this.employees.forEach(employee -> {
            System.out.println(employee);
            System.out.println(" ");
        });
    }

    @Override
    public void print() {
        this.employees.forEach(employee -> {
            System.out.println(employee);
            System.out.println(" ");
        });
    }

}
