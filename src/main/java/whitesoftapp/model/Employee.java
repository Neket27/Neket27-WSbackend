package whitesoftapp.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "EMPLOYEE")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
@ApiModel("Модель сотрудника")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank(message = "Name is mandatory")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    private String lastName;

    private String description;

    @ElementCollection
    @NotNull(message = "Characteristics are mandatory")
    private List<String> characteristics;

    @ManyToOne
    @ApiModelProperty("Должность сотрудника")
    @NotNull(message = "Post is mandatory")
    private Post post;

    @NotNull(message = "Contacts are mandatory")
    @Embedded
    private Contacts contacts;

    @NotNull
    private JobType jobType;

}


