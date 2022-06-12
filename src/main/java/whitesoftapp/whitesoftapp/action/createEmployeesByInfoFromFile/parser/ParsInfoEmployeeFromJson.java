package whitesoftapp.whitesoftapp.action.createEmployeesByInfoFromFile.parser;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;;
import whitesoftapp.whitesoftapp.model.Employee;
import whitesoftapp.whitesoftapp.model.Post;
import whitesoftapp.whitesoftapp.model.dtos.employee.EmployeeDto;
import whitesoftapp.whitesoftapp.repository.InMemoryPost;

@RequiredArgsConstructor
@Component
public class ParsInfoEmployeeFromJson {

    private final InMemoryPost inMemoryPost;

    public EmployeeDto dataEmployee(Employee employee) {
        Post post = inMemoryPost.get(employee.getId());
        return EmployeeDto.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .description(employee.getDescription())
                .characteristics(employee.getCharacteristics())
                .post(post)
                .build();
    }
}
