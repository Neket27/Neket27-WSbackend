package whitesoftapp.exception;

public class ApiRequestExceptionEmployee extends RuntimeException{

    public ApiRequestExceptionEmployee(String message) {
        super(message);
    }

    public ApiRequestExceptionEmployee(String message, Throwable cause) {
        super(message, cause);
    }
}
