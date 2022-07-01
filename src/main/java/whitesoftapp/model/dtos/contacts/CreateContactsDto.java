package whitesoftapp.model.dtos.contacts;

import lombok.Data;

import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.util.UUID;

@Data
public class CreateContactsDto {

    private UUID id;
    @Pattern(regexp = "(^$|\\d{10})")
    private String phone;
    @Email
    private String email;
    @Email
    private String workEmail;
}
