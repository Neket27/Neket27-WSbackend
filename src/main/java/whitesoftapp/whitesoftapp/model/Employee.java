package whitesoftapp.whitesoftapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.util.List;


//@Entity
//@Table(name = "EMPLOYEE")
@Embeddable
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class Employee {
    private  String firstName;
    private  String lastName;
    private  String description;
    private  List<String> characteristics;
    private Post post;

}


