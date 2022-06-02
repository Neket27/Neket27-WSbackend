package model.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class CreatePostDto implements DtoEntity {
    private UUID id;
    private String name;

    public CreatePostDto(){}
}
