package whitesoftapp.model.dtos.contacts;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.validation.constraints.Pattern;
import java.util.UUID;

@Data
@Builder
public class ContactsDto {
    private UUID id;
    private String phone;
    private String email;
    private String workEmail;
}
