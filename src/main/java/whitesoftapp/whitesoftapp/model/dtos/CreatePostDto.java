package whitesoftapp.whitesoftapp.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreatePostDto implements DtoEntity {
    private UUID id;
    private String name;

}
