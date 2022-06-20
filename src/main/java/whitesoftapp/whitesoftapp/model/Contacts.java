package whitesoftapp.whitesoftapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class Contacts {

    @Id
    private UUID id;
//    @Pattern(regexp = "(^$|\\d{10})")
    private String phone;
//    @Pattern(regexp = "^(.+)@(\\S+)$")
    private String email;
//    @Pattern(regexp = "^(.+)@(\\S+)$")
    private String workEmail;
}
