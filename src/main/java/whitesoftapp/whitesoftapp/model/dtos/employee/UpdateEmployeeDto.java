package whitesoftapp.whitesoftapp.model.dtos.employee;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import whitesoftapp.whitesoftapp.model.JobType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateEmployeeDto {

    @JsonProperty("postId")
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
    private UUID postId;
    @NotNull
    private UUID contactsId;
    @NotNull
    private JobType jobType;
}
