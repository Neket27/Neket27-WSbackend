package whitesoftapp.model.dtos.employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import whitesoftapp.model.JobType;
import whitesoftapp.model.dtos.contacts.ContactsDto;
import whitesoftapp.model.dtos.post.PostDto;

import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDto {

    private UUID id;
    @Pattern(regexp = "[A-Za-zА-Яа-яЁё]")
    private String firstName;
    @Pattern(regexp = "[A-Za-zА-Яа-яЁё]")
    private String lastName;
    private String description;
    private List<String> characteristics;
    private PostDto post;
    private ContactsDto contacts;
    private JobType jobType;

}
