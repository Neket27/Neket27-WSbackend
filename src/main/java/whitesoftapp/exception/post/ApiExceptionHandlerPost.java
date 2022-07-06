package whitesoftapp.exception.post;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandlerPost {

    @ExceptionHandler(value = {ErrorPost.class})
    public ResponseEntity<Object> handleApiRequestException(ErrorPost e) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ApiExceptionPost apiException = new ApiExceptionPost(e.getMessage(), httpStatus);
        return new ResponseEntity<>(apiException, httpStatus);
    }
}
