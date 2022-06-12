package whitesoftapp.whitesoftapp.model.dtos.employee;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import whitesoftapp.whitesoftapp.model.Contacts;
import whitesoftapp.whitesoftapp.model.JobType;
import whitesoftapp.whitesoftapp.model.Post;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("Модель сотрудник")
public class EmployeeDto {

    private UUID id;
    private String firstName;
    private String lastName;
    private String description;
    private List<String> characteristics;
    private Post post;
    private Contacts contacts;
    private JobType jobType;

}
