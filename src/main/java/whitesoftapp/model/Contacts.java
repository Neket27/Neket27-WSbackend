package whitesoftapp.model;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Contacts {

    @Id
    private UUID id;
    @NotBlank
    private String phone;
    @NotBlank
    private String email;
    @NotBlank
    private String workEmail;
}
