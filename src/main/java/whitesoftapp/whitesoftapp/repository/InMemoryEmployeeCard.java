package whitesoftapp.whitesoftapp.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import whitesoftapp.whitesoftapp.model.Employee;
import whitesoftapp.whitesoftapp.service.EmployeeCardService;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
@RequiredArgsConstructor
@Repository
public class InMemoryEmployeeCard implements EmployeeCardService {

    private final List<Employee> employees;

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
    public void set(UUID id,Employee updateEmployee) {
        UUID idd= UUID.fromString("762d15a5-3bc9-43ef-ae96-02a680a557d0");
        for (int i=0;i<employees.size();i++){
            if(employees.get(i).getPost().getId().equals(id)) {
                employees.set(i, updateEmployee);
                break;
            }
        }

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
        List<Employee> listEmployees=new ArrayList<>();
        listEmployees.addAll(this.employees);
        listEmployees.sort(comparatorInFirstNameAndLastName());
        listEmployees.forEach(employee -> {
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
