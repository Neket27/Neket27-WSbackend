package whitesoftapp.whitesoftapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandlerEmployee {

    @ExceptionHandler(value = {ApiRequestExceptionEmployee.class})
    public ResponseEntity<Object> handleApiRequestException(ApiRequestExceptionEmployee e) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ApiExceptionEmployee apiException = new ApiExceptionEmployee(e.getMessage(), httpStatus);
        return new ResponseEntity<>(apiException, httpStatus);
    }
}
