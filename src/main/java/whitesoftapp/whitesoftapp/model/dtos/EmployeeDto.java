package whitesoftapp.whitesoftapp.model.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import whitesoftapp.whitesoftapp.model.Post;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDto {

    @JsonProperty("postId")
    private UUID id;
    private String firstName;
    private String lastName;
    private String description;
    private List<String> characteristics;
    private Post post;
}
