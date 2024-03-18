package ifmo.block3;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Getter
public class Room {
    private final String name;
    private final int width;
    private final int length;
    private final int height;
    private final Map<String, Furniture> furnitureMap = new HashMap<>();
    private final Map<String, Creature> creatureMap = new HashMap<>();

    public Room(String name, int width, int length, int height){
        if (name == null || name.isEmpty() || name.isBlank() || width<=0 || length <=0 || height <=0){
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.width = width;
        this.length = length;
        this.height = height;

    }

    public boolean enterRoom(Creature creature) {
        if (creature != null && creature.getHeight() <= this.height) {
            creatureMap.put(creature.getName(), creature);
            return true;
        }
        return false;
    }

    public Creature exitRoom(String creatureName) {
        return this.creatureMap.remove(creatureName);
    }

    public boolean addFurniture(Furniture furniture) {
        if (furniture != null && furniture.getHeight() <= this.height && furniture.getWidth() <= this.width && furniture.getLength() <= this.length) {
            this.furnitureMap.put(furniture.getName(), furniture);
            return true;
        }
        return false;
    }

    public Furniture getFurniture(String furnitureName) {
        return this.furnitureMap.get(furnitureName);
    }
}
