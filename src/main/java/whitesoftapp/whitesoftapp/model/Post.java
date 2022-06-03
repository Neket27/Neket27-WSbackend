package whitesoftapp.whitesoftapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

//@Embeddable
@Entity
//@Table(name = "POST")
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
@Component
public class Post {
    @Id
    @Column(name = "id", nullable = false)
      private UUID id;
    private String name;


}
