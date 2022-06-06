package whitesoftapp.whitesoftapp.apiExceptionHandler;

import lombok.Builder;
import lombok.Data;
import whitesoftapp.whitesoftapp.model.dtos.DtoEntity;

@Data
@Builder
public class ResponseDTO<T> implements DtoEntity {
    private String status;

    @Builder.Default
    private String message = "Success!";

    private T body;
}
