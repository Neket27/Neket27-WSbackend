package whitesoftapp.whitesoftapp.service;

import org.springframework.stereotype.Service;
import whitesoftapp.whitesoftapp.model.Employee;
import whitesoftapp.whitesoftapp.model.dtos.DtoEntity;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
public interface EmployeeCardService {


    Employee get(UUID id) throws Exception;

    Employee get(String firstName, String lastName) throws Exception;

    void removeFromListNull();

    void set(UUID id, Employee updateEmployee);

    void add(Employee employee);

    void add(List<Employee> employees);

    Comparator<? super Employee> comparatorInFirstNameAndLastName();

    void printSortedByFirstAndLastName();

    void print();

    List<Employee> getList();
}
