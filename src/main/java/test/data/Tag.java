package test.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by zsmirnova on 26/03/2019.
 */
@Data(staticConstructor = "tag")
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Tag {

    private String id;
    private String name;
}
