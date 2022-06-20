package whitesoftapp.whitesoftapp.model.dtos.employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import whitesoftapp.whitesoftapp.model.JobType;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDto {

    private UUID id;
    private String firstName;
    private String lastName;
    private String description;
    private List<String> characteristics;
    private UUID postId;
    private UUID contactsId;
    private JobType jobType;

}
