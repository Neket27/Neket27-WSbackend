package whitesoftapp.model.dtos.employee;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import whitesoftapp.model.JobType;
import whitesoftapp.model.dtos.contacts.ContactsDto;
import whitesoftapp.model.dtos.post.PostDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateEmployeeDto {

    private UUID id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String description;
    @NotNull
    private List<String> characteristics;
    @NotNull
    private PostDto post;
    @NotNull
    private ContactsDto contacts;
    @NotNull
    private JobType jobType;
}
