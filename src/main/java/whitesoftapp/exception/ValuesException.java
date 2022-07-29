package whitesoftapp.exception;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@RequiredArgsConstructor
public class ValuesException {

    private final String massage;
    private final HttpStatus httpStatus;
}
