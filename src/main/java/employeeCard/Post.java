package employeeCard;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Data
@Component
public class Post {
    private UUID id;
    private String name;

    public Post(){}

    public Post(UUID id, String name) {
        this.id=id;
        this.name=name;
    }

}
