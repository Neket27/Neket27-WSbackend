package model;

import employeeCard.Employee;
import service.EmployeeCardService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.util.*;

@Data
@RequiredArgsConstructor
public class InMemoryEmployeeCardService implements EmployeeCardService {
    private  List<Employee> employees = new ArrayList<>();

    public InMemoryEmployeeCardService(Employee employee){
        this.employees.add(employee);
    }
    @Override
    public Employee get(UUID id) {
        Optional<Employee>  OneEmployeeInList=  employees.stream()
                .filter(employee -> (employee.getPost().getId()).equals(id)).findFirst();
        return OneEmployeeInList.get();
    }

    @Override
    public Employee get(String firstName, String lastName) {
        Optional<Employee> OneEmployeeInList=employees.stream()
                .filter(employee ->(employee.getFirstName().equals(firstName))&&(employee.getLastName().equals(lastName))).findFirst();
        return OneEmployeeInList.get();
    }

    @Override
    public void add(Employee employee){
        this.employees.add(employee);
    }
    @Override
    public void add(List<Employee> employees) {
        this.employees.addAll(employees);
    }

    @Override
    public Comparator<? super Employee> comparatorInFirstNameAndLastName() {
        Comparator<Employee> employeeNameComparator
                = Comparator.comparing(Employee::getFirstName).thenComparing(Employee::getLastName);
        Collections.sort(employees, employeeNameComparator);
        return employeeNameComparator;
    }
    @Override
    public void print(List<Employee> employees) {
        employees.sort(comparatorInFirstNameAndLastName());
        employees.forEach(System.out::println);
    }
}
