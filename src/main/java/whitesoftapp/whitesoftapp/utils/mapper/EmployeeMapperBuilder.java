package whitesoftapp.whitesoftapp.utils.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import whitesoftapp.whitesoftapp.argument.CreateEmployeeArgument;
import whitesoftapp.whitesoftapp.argument.UpdateEmployeeArgument;
import whitesoftapp.whitesoftapp.model.Contacts;
import whitesoftapp.whitesoftapp.model.Employee;
import whitesoftapp.whitesoftapp.model.Post;
import whitesoftapp.whitesoftapp.model.dtos.employee.EmployeeDto;
import whitesoftapp.whitesoftapp.repository.InMemoryContacts;
import whitesoftapp.whitesoftapp.repository.InMemoryPost;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class EmployeeMapperBuilder {

    private final InMemoryPost inMemoryPost;
    private final InMemoryContacts inMemoryContacts;

    private Post post;
    private Contacts contacts;

    private void createObjectsWithIdIfObjectsNo(UUID postId, UUID contactsId) {
        post = inMemoryPost.get(postId);
        contacts = inMemoryContacts.get(contactsId);

        if (post == null) {
            post = Post.builder()
                    .id(postId)
                    .build();
        }

        if (contacts == null) {
            contacts = Contacts.builder()
                    .id(contactsId)
                    .build();
        }
    }

    public EmployeeDto toDto(Employee employee) {
        return EmployeeDto.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .description(employee.getDescription())
                .characteristics(employee.getCharacteristics())
                .postId(employee.getPost().getId())
                .contactsId(employee.getContacts().getId())
                .jobType(employee.getJobType())
                .build();
    }

    public Employee toEntity(EmployeeDto employeeDto) {

       createObjectsWithIdIfObjectsNo(employeeDto.getPostId(),employeeDto.getContactsId());

        return Employee.builder()
                .firstName(employeeDto.getFirstName())
                .lastName(employeeDto.getLastName())
                .description(employeeDto.getDescription())
                .characteristics(employeeDto.getCharacteristics())
                .post(post)
                .contacts(contacts)
                .jobType(employeeDto.getJobType())
                .build();
    }

    public Employee toEntity(CreateEmployeeArgument createEmployeeArgument) {

        createObjectsWithIdIfObjectsNo(createEmployeeArgument.getPostId(), createEmployeeArgument.getContactsId());

        return Employee.builder()
                .firstName(createEmployeeArgument.getFirstName())
                .lastName(createEmployeeArgument.getLastName())
                .description(createEmployeeArgument.getDescription())
                .characteristics(createEmployeeArgument.getCharacteristics())
                .post(post)
                .contacts(contacts)
                .jobType(createEmployeeArgument.getJobType())
                .build();
    }

    public Employee toEntity(UpdateEmployeeArgument updateEmployeeArgument) {

        createObjectsWithIdIfObjectsNo(updateEmployeeArgument.getPostId(), updateEmployeeArgument.getContactsId());

        return Employee.builder()
                .id(updateEmployeeArgument.getId())
                .firstName(updateEmployeeArgument.getFirstName())
                .lastName(updateEmployeeArgument.getLastName())
                .description(updateEmployeeArgument.getDescription())
                .characteristics(updateEmployeeArgument.getCharacteristics())
                .post(post)
                .contacts(contacts)
                .jobType(updateEmployeeArgument.getJobType())
                .build();
    }

}
