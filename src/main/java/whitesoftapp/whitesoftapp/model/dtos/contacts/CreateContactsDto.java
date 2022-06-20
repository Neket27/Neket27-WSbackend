package whitesoftapp.whitesoftapp.model.dtos.contacts;

import lombok.Data;

import javax.persistence.Id;
import javax.validation.constraints.Pattern;
import java.util.UUID;

@Data
public class CreateContactsDto {
    @Id
    private UUID id;
    @Pattern(regexp = "(^$|\\d{10})")
    private String phone;
    @Pattern(regexp = "^(.+)@(\\S+)$")
    private String email;
    @Pattern(regexp = "^(.+)@(\\S+)$")
    private String workEmail;
}
