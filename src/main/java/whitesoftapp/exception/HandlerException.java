package whitesoftapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandlerException {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {ErrorException.class})
    public ValuesException handleApiRequestException(ErrorException e) {
        return new ValuesException(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
