package whitesoftapp.whitesoftapp.notFoundException;

import java.util.UUID;

public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(UUID id) {
        super("Could not find employee " + id);
    }

    public EmployeeNotFoundException(String message){
        super(message);
    }
}