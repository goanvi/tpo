package ifmo;

import ifmo.block3.*;

public class Main {
    public static void main(String[] args) {
        Human artur = new Human("Артур");
        artur.changeCondition(CreatureConditionEnum.NERVOUS);

        Creature unknown = new Creature("неизвестный");

        Creature human = new Creature("человек");
        human.getBodyPartsMap().put("правая голова", new BodyPart("правая голова", 30, 20, BodyPartEnum.HEAD));
        human.getBodyPartsMap().put("левая голова", new BodyPart("левая голова", 30, 20, BodyPartEnum.HEAD));
        human.getBodyPartsMap().put("туловище",new BodyPart("туловище", 50, 20, BodyPartEnum.TORSO));
        human.getBodyPartsMap().put("левая рука",new BodyPart("левая рука", 30, 5, BodyPartEnum.ARM));
        human.getBodyPartsMap().put("правая рука",new BodyPart("правая рука", 30, 5, BodyPartEnum.ARM));
        human.getBodyPartsMap().put("левая нога",new BodyPart("левая нога", 50, 10, BodyPartEnum.LEG));
        human.getBodyPartsMap().put("правая нога",new BodyPart("правая нога", 50, 10, BodyPartEnum.LEG));
        human.changeCondition(CreatureConditionEnum.CHEERFUL);

        ControlPanel controlPanel = new ControlPanel("панель управления", 10, 10, 2);
        Chair chair = new Chair("кресло", 5,5,1);
        Room room = new Room("комната", 100, 100, 10);
        room.addFurniture(chair);
        room.addFurniture(controlPanel);
        room.enterRoom(human);
        chair.setOccupant(human);




    }
}

