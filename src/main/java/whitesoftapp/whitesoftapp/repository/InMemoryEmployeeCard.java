package whitesoftapp.whitesoftapp.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import whitesoftapp.whitesoftapp.NotFoundException.EmployeeNotFoundException;
import whitesoftapp.whitesoftapp.model.Employee;
import java.util.*;

@RequiredArgsConstructor
@Repository
public class InMemoryEmployeeCard  {

    private final List<Employee> employees;

    public Employee get(UUID id) throws Exception {
        Employee e = this.employees.stream()
                .filter(employee -> (employee.getPost().getId()).equals(id)).findFirst().orElse(null);
        if (e == null)
//            throw new Exception("There is no element with such an id");
            throw new EmployeeNotFoundException(id);
        return e;
    }

    public Employee get(String firstName, String lastName) throws Exception {
        Employee e = this.employees.stream()
                .filter(employee -> (employee.getFirstName().equals(firstName)) && (employee.getLastName().equals(lastName))).findFirst().orElse(null);
        if (e == null)
            throw new Exception("There is no element with such firstName and lastName");
        return e;
    }

    public void removeFromListNull() {
        employees.removeAll(Collections.singleton(null));
    }

    public void set(UUID id, Employee updateEmployee) {
        UUID idd = UUID.fromString("762d15a5-3bc9-43ef-ae96-02a680a557d0");
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getPost().getId().equals(id)) {
                employees.set(i, updateEmployee);
                break;
            }
        }

    }

    public void add(Employee employee) {
        this.employees.add(employee);
    }

    public void add(List<Employee> employees) {
        this.employees.addAll(employees);
    }

    public Comparator<? super Employee> comparatorInFirstNameAndLastName() {
        return Comparator.comparing(Employee::getFirstName).thenComparing(Employee::getLastName);
    }

    public void printSortedByFirstAndLastName() {
        List<Employee> listEmployees = new ArrayList<>();
        listEmployees.addAll(this.employees);
        listEmployees.sort(comparatorInFirstNameAndLastName());
        listEmployees.forEach(employee -> {
            System.out.println(employee);
            System.out.println(" ");
        });
    }

    public void print() {
        this.employees.forEach(employee -> {
            System.out.println(employee);
            System.out.println(" ");
        });
    }

    public List<Employee> getList() {
        return employees;
    }

}
