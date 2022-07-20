package whitesoftapp.service.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import whitesoftapp.action.updateDataEmployee.UpdateData;
import whitesoftapp.arguments.CreateEmployeeArgument;
import whitesoftapp.arguments.UpdateEmployeeArgument;
import whitesoftapp.controller.utils.mapper.employee.EmployeeMapper;
import whitesoftapp.exception.ErrorException;
import whitesoftapp.model.Employee;
import whitesoftapp.model.Post;
import whitesoftapp.repository.EmployeeRepository;
import whitesoftapp.service.post.PostService;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final PostService postService;
    private final UpdateData updateData;

    public Employee create(CreateEmployeeArgument createEmployeeArgument) {
        Employee employee = employeeMapper.toEntityFromUpdateArgument(createEmployeeArgument);
        Post post = postService.getById(createEmployeeArgument.getPostId());
        employee.setPost(post);
        return employeeRepository.save(employee);
    }

    public Employee update(UUID id, UpdateEmployeeArgument updateEmployeeArgument) {
        Employee employeeUpdated = employeeMapper.toEntityFromUpdateArgument(updateEmployeeArgument);
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ErrorException("Нет Работника с таким id"));
        updateData.updateFields(employee, employeeUpdated);
        return employeeRepository.save(employeeUpdated);
    }

    public Employee getById(UUID id) {
        return employeeRepository.findById(id).orElseThrow(() -> new ErrorException("Нет Работника с таким id"));
    }

    public List<Employee> getList() {
        return employeeRepository.findAll();
    }

    public void remove(UUID id) {
        employeeRepository.findById(id).orElseThrow(() -> new ErrorException("Нет Работника с таким id"));
        employeeRepository.deleteById(id);
    }

    public Comparator<? super Employee> comparatorInFirstNameAndLastName() {
        return Comparator.comparing(Employee::getFirstName).thenComparing(Employee::getLastName);
    }

    public void printSortedByFirstAndLastName() {
        List<Employee> listEmployees = employeeRepository.findAll();
        listEmployees.sort(comparatorInFirstNameAndLastName());
        listEmployees.forEach(employee -> {
            System.out.println(employee);
            System.out.println(" ");
        });
    }

}
