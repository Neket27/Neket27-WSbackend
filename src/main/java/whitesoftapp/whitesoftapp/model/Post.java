package whitesoftapp.whitesoftapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Embeddable
public class Post {
    @Id
    @Column(name = "id", nullable = false)
    @NotNull(message = "Post id = null")
    private UUID id;

    @NotBlank(message = "Post name is mandatory")
    private String name;

}
