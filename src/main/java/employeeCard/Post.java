package employeeCard;

import lombok.Data;
import java.util.UUID;

@Data
public class Post {
    private UUID id;
    private String name;

    public Post(UUID id, String name) {
        this.id=id;
        this.name=name;
    }

}
