package whitesoftapp.whitesoftapp.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import whitesoftapp.whitesoftapp.model.Employee;
import whitesoftapp.whitesoftapp.notFoundException.EmployeeNotFoundException;
import java.util.*;

@RequiredArgsConstructor
@Repository
public class InMemoryEmployeeCard  {

    private final HashMap<UUID,Employee> employees;

    public Employee get(UUID id)  {
        Employee employee=employees.get(id);
        if(employee==null){
            throw new EmployeeNotFoundException(id);
        }
        return employee;
    }

    public Employee get(String firstName, String lastName) throws Exception {
        Employee employee=null;
        for (Map.Entry<UUID, Employee> entry : employees.entrySet()) {
            Employee value = entry.getValue();
            if(value.getFirstName()==firstName && value.getLastName()==lastName)
                employee =value;
        }
        if (employee == null)
            throw new EmployeeNotFoundException("There is no element with such "+firstName+" and "+lastName+"");
        return employee;

    }

    public void set(UUID id, Employee updateEmployee) {

        employees.put(id,updateEmployee);
    }

    public void remove(UUID id){
        employees.remove(id);
    }

    public void add(UUID id,Employee employee) {
        this.employees.put(id,employee);
    }

    public Comparator<? super Employee> comparatorInFirstNameAndLastName() {
        return Comparator.comparing(Employee::getFirstName).thenComparing(Employee::getLastName);
    }

    public void printSortedByFirstAndLastName() {
        List<Employee> listEmployees = new ArrayList<>(employees.values());
        listEmployees.sort(comparatorInFirstNameAndLastName());
        listEmployees.forEach(employee -> {
            System.out.println(employee);
            System.out.println(" ");
        });
    }

    public void print() {
        System.out.println("Employees: "+employees);
    }

    public HashMap<UUID,Employee> getList() {
        return employees;
    }

}
