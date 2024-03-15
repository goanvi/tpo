package ifmo.block3;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BodyPart {
    private final String name;
    private int length;
    private int weight;
    private BodyPartEnum bodyPartEnum;
}
