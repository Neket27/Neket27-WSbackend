package whitesoftapp.exception.post;

public class ErrorPost extends RuntimeException{

    public ErrorPost(String message) {
        super(message);
    }

    public ErrorPost(String message, Throwable cause) {
        super(message, cause);
    }
}
