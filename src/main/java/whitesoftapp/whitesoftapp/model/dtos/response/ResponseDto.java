package whitesoftapp.whitesoftapp.model.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ResponseDto<T> {
    private String status;

    @Builder.Default
    private String message = "Success!";

    private T body;
}
