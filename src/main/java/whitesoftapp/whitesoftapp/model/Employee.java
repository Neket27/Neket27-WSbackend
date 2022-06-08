package whitesoftapp.whitesoftapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "EMPLOYEE")
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class Employee {

    @JsonProperty("postId")
    @Id
    private UUID id;
    private String firstName;
    private String lastName;
    private String description;
    @OneToMany(targetEntity = Employee.class)
    private List<String> characteristics;
    @Embedded
    private Post post;

}


