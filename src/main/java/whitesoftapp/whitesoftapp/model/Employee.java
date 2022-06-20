package whitesoftapp.whitesoftapp.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "EMPLOYEE")
@NoArgsConstructor
@Data
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

    @OneToMany(targetEntity = Employee.class)
    @NotNull(message = "Characteristics are mandatory")
    private List<String> characteristics;

    @Embedded
    @ApiModelProperty("Должность сотрудника")
    @NotNull(message = "Post is mandatory")
    private Post post;

    @Embedded
    @NotBlank(message = "Contacts are mandatory")
    private Contacts contacts;

    @Embedded
    @NotNull
    private JobType jobType;

}


