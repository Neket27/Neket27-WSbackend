package whitesoftapp.whitesoftapp.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class JobType {

    public final String PERMANENT;
    public final String TEMPORARY;
    public final String CONTRACT;
    public final String FULL_TIME;
    public final String PART_TIME;

}
