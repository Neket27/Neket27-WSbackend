package whitesoftapp.exception.post;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@RequiredArgsConstructor
public class ApiExceptionPost {

    private final String massage;
}
