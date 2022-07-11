package whitesoftapp.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import whitesoftapp.model.Employee;

import java.util.*;

@RequiredArgsConstructor
@Repository
public class InMemoryEmployeeCard {

    private final Map<UUID, Employee> employees;

    public Employee get(UUID id) {
        return employees.get(id);
    }

    public List<Employee> get(String firstName, String lastName) throws Exception {
        List<Employee> employeeList = null;
        for (Map.Entry<UUID, Employee> entry : employees.entrySet()) {
            Employee value = entry.getValue();
            if (value.getFirstName() == firstName && value.getLastName() == lastName)
                employeeList.add(value);
        }
        if (employeeList == null)
            throw new Exception("There is no element with such " + firstName + " and " + lastName + "");
        return employeeList;
    }

    public void remove(UUID id) {
        employees.remove(id);
    }

    public void add(Employee employee) {
        employee.setId(java.util.UUID.randomUUID());
        this.employees.put(employee.getId(), employee);
    }

    public void put(UUID id, Employee employee) {
        this.employees.put(id, employee);
    }

    public Map<UUID, Employee> getMap() {
        return employees;
    }

}
