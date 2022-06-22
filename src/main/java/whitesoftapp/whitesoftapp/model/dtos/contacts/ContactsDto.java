package whitesoftapp.whitesoftapp.model.dtos.contacts;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.validation.constraints.Pattern;
import java.util.UUID;

@Data
@Builder
public class ContactsDto {

    @Id
    private UUID id;
//    @Pattern(regexp = "(^$|\\d{10})")
    private String phone;
//    @Pattern(regexp = "^(.+)@(\\S+)$")
    private String email;
//    @Pattern(regexp = "^(.+)@(\\S+)$")
    private String workEmail;
}
