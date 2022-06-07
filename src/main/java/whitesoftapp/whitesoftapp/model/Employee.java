package whitesoftapp.whitesoftapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Embeddable;
import java.util.List;
import java.util.UUID;


//@Entity
//@Table(name = "EMPLOYEE")
@Embeddable
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class Employee {

    @JsonProperty("postId")
    private UUID id;
    private  String firstName;
    private  String lastName;
    private  String description;
    private  List<String> characteristics;
    private Post post;

}


