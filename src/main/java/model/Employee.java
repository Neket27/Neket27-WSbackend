package model;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;


@Entity
@Table(name = "EMPLOYEE")
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
@Component
public class Employee {
    private  String firstName;
    private  String lastName;
    private  String description;
    private  List<String> characteristics;
    private Post post;

}


