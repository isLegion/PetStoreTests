package test.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by zsmirnova on 26/03/2019.
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Pet {

  private String id;
  private Category category;
  private String name;
  private List<String> photoUrls;
  private List<Tag> tags;
  private Status status;
}


