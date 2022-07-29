package whitesoftapp.service.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import whitesoftapp.action.unificationData.UnificationDataEmployeeAndPost;
import whitesoftapp.arguments.CreateEmployeeArgument;
import whitesoftapp.arguments.UpdateEmployeeArgument;
import whitesoftapp.exception.ErrorException;
import whitesoftapp.model.Employee;
import whitesoftapp.repository.EmployeeRepository;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final UnificationDataEmployeeAndPost unificationDataEmployee;

    public Employee create(CreateEmployeeArgument createEmployeeArgument) {
        Employee employee = unificationDataEmployee.unificationDataEmployeeWitchPost(createEmployeeArgument);
        return employeeRepository.save(employee);
    }

    public Employee update(UUID id, UpdateEmployeeArgument updateEmployeeArgument) {
        Employee employee = getById(id);
        Employee updatedData = unificationDataEmployee.unificationDataEmployeeWitchPost(updateEmployeeArgument);

        employee.setFirstName(updatedData.getFirstName());
        employee.setLastName(updatedData.getLastName());
        employee.setDescription(updatedData.getDescription());
        employee.setContacts(updatedData.getContacts());
        employee.setCharacteristics(updatedData.getCharacteristics());
        employee.setPost(updatedData.getPost());
        employee.setJobType(updatedData.getJobType());

        return employeeRepository.save(employee);
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
