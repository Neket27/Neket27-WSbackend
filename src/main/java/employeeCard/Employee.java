package employeeCard;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Data
@Builder
public class Employee  {
    private String firstName;
    private String lastName;
    private String description;
    private List<String> characteristics= new ArrayList<>();
    private Post post;

    public Employee(String firstName, String lastName, String description, List<String> characteristics,Post post) {
        this.firstName=firstName;
        this.lastName=lastName;
        this.description=description;
        this.characteristics.addAll(characteristics);
        this.post=post;
    }
}


