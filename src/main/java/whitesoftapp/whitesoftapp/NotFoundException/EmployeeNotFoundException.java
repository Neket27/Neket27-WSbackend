package whitesoftapp.whitesoftapp.NotFoundException;

import java.util.UUID;

public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(UUID id) {
        super("Could not find employee " + id);
    }
}