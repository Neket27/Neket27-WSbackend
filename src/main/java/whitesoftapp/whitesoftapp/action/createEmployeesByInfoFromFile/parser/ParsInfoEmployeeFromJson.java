package whitesoftapp.whitesoftapp.action.createEmployeesByInfoFromFile.parser;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;;
import whitesoftapp.whitesoftapp.model.Employee;
import whitesoftapp.whitesoftapp.model.Post;
import whitesoftapp.whitesoftapp.model.dtos.employee.EmployeeDto;
import whitesoftapp.whitesoftapp.repository.InMemoryPost;
import whitesoftapp.whitesoftapp.utils.mapper.EmployeeMapper;

@RequiredArgsConstructor
@Component
public class ParsInfoEmployeeFromJson {

    private final EmployeeMapper employeeMapper;
    private final InMemoryPost inMemoryPost;

    public EmployeeDto dataEmployee(Employee employee) {
        Post post = inMemoryPost.get(employee.getId());
        employee.setPost(post);
        return employeeMapper.toDto(employee);
    }
}
