package whitesoftapp.whitesoftapp.model.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import whitesoftapp.whitesoftapp.model.Post;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateEmployeeDto implements DtoEntity {
    private String title;
    private String firstName;
    private String lastName;
    private String description;
    private List<String> characteristics;
    private Post post;

}
