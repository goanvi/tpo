package ifmo.block3;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Chair extends Furniture {
    private Creature occupant;
    private boolean occupied;

    public Chair(String name, int width, int length, int height) {
        super(name, width, length, height);
    }

    public void setOccupant(Creature occupant) {
        this.occupied = true;
        this.occupant = occupant;
    }

    public void resetOccupant() {
        this.occupant = null;
        this.occupied = false;
    }
}
