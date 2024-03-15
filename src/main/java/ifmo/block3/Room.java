package ifmo.block3;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public class Room {
    private final String name;
    private final int width;
    private final int length;
    private final int height;
    private final Map<String, Furniture> furnitureMap = new HashMap<>();
    private final Map<String, Creature> creatureMap = new HashMap<>();

    public boolean enterRoom(@NonNull Creature creature) {
        if (creature.getHeight() <= this.height) {
            creatureMap.put(creature.getName(), creature);
            return true;
        }
        return false;
    }

    public Creature exitRoom(String creatureName) {
        return this.creatureMap.remove(creatureName);
    }

    public boolean addFurniture(@NonNull Furniture furniture) {
        if (furniture.getHeight() <= this.height && furniture.getWidth() <= this.width && furniture.getLength() <= this.length) {
            this.furnitureMap.put(furniture.getName(), furniture);
            return true;
        }
        return false;
    }

    public Furniture getFurniture(String furnitureName) {
        return this.furnitureMap.get(furnitureName);
    }
}
