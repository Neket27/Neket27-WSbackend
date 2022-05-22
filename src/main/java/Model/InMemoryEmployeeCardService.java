package Model;

import EmployeeCard.Employee;
import Service.EmployeeCardService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.util.*;
import java.util.stream.Collectors;

@Data
@RequiredArgsConstructor
public class InMemoryEmployeeCardService implements EmployeeCardService {
    private  List<Employee> employees = new ArrayList<>();

    public InMemoryEmployeeCardService(List<Employee> employees) {
        for (int index = 0; index < employees.size(); index++)
            this.employees.add(employees.get(index));
    }

    public InMemoryEmployeeCardService(Employee employee){
        this.employees.add(employee);
    }

    @Override
    public Employee get(UUID id) {
       List<Employee> OneEmployeeInList=  employees.stream()
                .filter(employee -> (employee.getPost().getId()).equals(id)).collect(Collectors.toList());
        return OneEmployeeInList.get(0);
    }

    @Override
    public Employee get(String firstName, String lastName) {
        List<Employee> OneEmployeeInList=employees.stream()
                .filter(employee ->(employee.getFirstName().equals(firstName))&&(employee.getLastName().equals(lastName))).collect(Collectors.toList());
        return OneEmployeeInList.get(0);
    }

    @Override
     public void add(Employee employee){
        this.employees.add(employee);
    }

}
