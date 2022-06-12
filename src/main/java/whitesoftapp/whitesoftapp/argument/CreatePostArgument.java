package whitesoftapp.whitesoftapp.argument;

import lombok.Builder;
import lombok.Data;
import lombok.Value;
import java.util.UUID;

@Value
@Data
@Builder
public class CreatePostArgument {

    private UUID id;
    private String name;
}
