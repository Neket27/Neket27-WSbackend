package whitesoftapp.arguments;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class CreatePostArgument {

    private UUID id;
    private String name;
}
