package whitesoftapp.model;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Post implements Serializable {
    @Id
    @NotNull(message = "Post id = null")
    private UUID id;

    @NotBlank(message = "Post name is mandatory")
    private String name;

}
