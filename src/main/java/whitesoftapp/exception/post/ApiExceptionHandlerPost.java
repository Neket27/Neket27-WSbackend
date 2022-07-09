package whitesoftapp.exception.post;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandlerPost {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {ErrorPost.class})
    public ApiExceptionPost handleApiRequestException(ErrorPost e) {
        return new ApiExceptionPost(e.getMessage());
    }
}
