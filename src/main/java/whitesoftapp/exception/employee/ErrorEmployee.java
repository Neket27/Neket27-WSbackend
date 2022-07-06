package whitesoftapp.exception.employee;

public class ErrorEmployee extends RuntimeException{

    public ErrorEmployee(String message) {
        super(message);
    }

    public ErrorEmployee(String message, Throwable cause) {
        super(message, cause);
    }
}
