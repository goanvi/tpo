package ifmo.block3;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class Creature {
    private final String name;
    private final int height;
    private final Map<String, BodyPart> bodyPartsMap = new HashMap<>();
    private CreatureConditionEnum condition;

    public Creature(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.height = 0;
        this.condition = CreatureConditionEnum.NORMAL;
    }

    public Creature(String name, int height) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }
        if (height <= 0) {
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.height = height;
        this.condition = CreatureConditionEnum.NORMAL;
    }

    public void changeCondition(CreatureConditionEnum condition) {
        this.condition = condition;
    }

    public int getWeight() {
        int weight = 0;
        for (Map.Entry<String, BodyPart> part : bodyPartsMap.entrySet()) {
            weight += part.getValue().getWeight();
        }
        return weight;
    }

}