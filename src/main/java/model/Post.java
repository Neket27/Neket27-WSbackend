package model;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "POST")
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
@Component
public class Post {
    private UUID id;
    private String name;

}
