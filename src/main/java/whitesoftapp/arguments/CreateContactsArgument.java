package whitesoftapp.arguments;

import javax.validation.constraints.NotBlank;

public class CreateContactsArgument {
    @NotBlank
    private String phone;
    @NotBlank
    private String email;
    @NotBlank
    private String workEmail;
}
