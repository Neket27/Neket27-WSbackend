package model.dtos;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import model.Post;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class CreateEmployeeDto implements DtoEntity {
    private String title;
    private String firstName;
    private String lastName;
    private String description;
    private List<String> characteristics;
    private  Post post;

    public CreateEmployeeDto(){}


}
