package whitesoftapp.model.dtos.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePostDto {
    @NotNull(message = "Post id = null")
    private UUID id;
    @NotBlank(message = "Post name is mandatory")
    private String name;

}
