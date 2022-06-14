package whitesoftapp.whitesoftapp.model.dtos.employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import whitesoftapp.whitesoftapp.model.Contacts;
import whitesoftapp.whitesoftapp.model.JobType;
import whitesoftapp.whitesoftapp.model.Post;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateEmployeeDto {

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String description;
    @NotNull
    private List<String> characteristics;
    @NotNull
    private Post post;
    @NotNull
    private Contacts contacts;
    @NotNull
    private JobType jobType;

}
