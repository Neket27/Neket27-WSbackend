package whitesoftapp.whitesoftapp.argument;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import whitesoftapp.whitesoftapp.model.JobType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UpdateEmployeeArgument {

    private UUID id;

    @NotBlank(message = "Name is mandatory")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    private String lastName;

    private String description;

    @NotNull(message = "Characteristics are mandatory")
    private List<String> characteristics;

    @NotNull(message = "Post is mandatory")
    private UUID postId;

    @NotNull(message = "Contacts are mandatory")
    private UUID contactsId;

    @NotNull(message = "JobType is mandatory")
    private JobType jobType;
}
