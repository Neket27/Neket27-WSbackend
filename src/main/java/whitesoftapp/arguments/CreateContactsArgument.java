package whitesoftapp.arguments;

import lombok.Builder;
import javax.persistence.Id;
import java.util.UUID;

@Builder
public class CreateContactsArgument {
    @Id
    private UUID id;
    private String phone;
    private String email;
    private String workEmail;

}
