package Dto;

import lombok.Data;
import java.util.UUID;

@Data
public class CreatePostDto {
    private UUID id;
    private String name;

    public CreatePostDto(UUID id, String name) {
        this.id=id;
        this.name=name;
    }
}
