package whitesoftapp.exception.employee;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandlerEmployee {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {ErrorEmployee.class})
    public ApiExceptionEmployee handleApiRequestException(ErrorEmployee e) {
        return new ApiExceptionEmployee(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
