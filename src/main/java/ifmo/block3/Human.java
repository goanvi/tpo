package ifmo.block3;

import java.util.Map;

public class Human extends Creature {

    public Human(String name) {
        super(name);
        this.setStandardParts(this.getBodyPartsMap());
    }

    public Human(String name, int height) {
        super(name, height);
        this.setStandardParts(this.getBodyPartsMap());
    }

    public Human(String name, int height, BodyPart... parts) {
        super(name, height);
        for (BodyPart part : parts) {
            this.getBodyPartsMap().put(part.getName(), part);
        }
    }

    public void meet(Creature creature) {
        if (!(creature instanceof Human)) {
            changeCondition(CreatureConditionEnum.SHOCKED);
        }
        //TODO Может что то еще добавить
    }

    public void goTo(Room room) {
        room.enterRoom(this);
    }

    public void goTo(Room prevRoom, Room newRoom) {
        if (prevRoom != null) {
            prevRoom.exitRoom(this.getName());
        }
        if (newRoom != null) {
            newRoom.enterRoom(this);
        }
    }

    private void setStandardParts(Map<String, BodyPart> map) {
        BodyPart part = new BodyPart("Голова", 30, 20, BodyPartEnum.HEAD);
        map.put(part.getName(), part);
        part = new BodyPart("Туловище", 50, 20, BodyPartEnum.TORSO);
        map.put(part.getName(), part);
        part = new BodyPart("Левая рука", 30, 5, BodyPartEnum.ARM);
        map.put(part.getName(), part);
        part = new BodyPart("Правая рука", 30, 5, BodyPartEnum.ARM);
        map.put(part.getName(), part);
        part = new BodyPart("Левая нога", 50, 10, BodyPartEnum.LEG);
        map.put(part.getName(), part);
        part = new BodyPart("Правая нога", 50, 10, BodyPartEnum.LEG);
        map.put(part.getName(), part);
    }
}
