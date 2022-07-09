package whitesoftapp.service.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import whitesoftapp.arguments.CreateEmployeeArgument;
import whitesoftapp.arguments.UpdateEmployeeArgument;
import whitesoftapp.controller.utils.mapper.employee.EmployeeMapper;
import whitesoftapp.exception.employee.ErrorEmployee;
import whitesoftapp.model.Employee;
import whitesoftapp.model.Post;
import whitesoftapp.repository.InMemoryEmployeeCard;
import whitesoftapp.service.post.PostService;

import java.util.*;

@RequiredArgsConstructor
@Service
public class EmployeeService {

    private final InMemoryEmployeeCard inMemoryEmployeeCard;
    private final EmployeeMapper employeeMapper;
    private final PostService postService;

    public Employee create(CreateEmployeeArgument createEmployeeArgument) {
        Employee employee = employeeMapper.toEntityFromUpdateArgument(createEmployeeArgument);
        Post post=postService.getById(createEmployeeArgument.getPostId());
        employee.setPost(post);
        inMemoryEmployeeCard.add(employee);
        return employee;
    }

    public Employee update(UUID id, UpdateEmployeeArgument updateEmployeeArgument) {
        Employee employee = employeeMapper.toEntityFromUpdateArgument(updateEmployeeArgument);
        inMemoryEmployeeCard.put(id, employee);
        return employee;
    }

      public Employee getById(UUID id) {
        Employee employee = inMemoryEmployeeCard.get(id);
        if (employee == null)
            throw new ErrorEmployee("Нет Работника с таким id");

        return employee;
    }

    public HashMap<UUID, Employee> getList() {
        return inMemoryEmployeeCard.getList();
    }

    public void remove(UUID id) {
        inMemoryEmployeeCard.remove(id);
    }

    public Comparator<? super Employee> comparatorInFirstNameAndLastName() {
        return Comparator.comparing(Employee::getFirstName).thenComparing(Employee::getLastName);
    }

    public void printSortedByFirstAndLastName() {
        List<Employee> listEmployees = new ArrayList<>(inMemoryEmployeeCard.getList().values());
        listEmployees.sort(comparatorInFirstNameAndLastName());
        listEmployees.forEach(employee -> {
            System.out.println(employee);
            System.out.println(" ");
        });
    }

}
