package EmployeeCard;

import lombok.Data;
import java.util.UUID;

@Data
public class Post  {
    UUID id;
    String name;

    public Post(UUID id, String name) {
        this.id=id;
        this.name=name;
    }


}
