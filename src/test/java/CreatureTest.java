import ifmo.block3.*;
import ifmo.block3.exceptions.AlreadyEngagedException;
import ifmo.block3.exceptions.NoInteractionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CreatureTest {
    Creature creature;
    Human human;

    @BeforeEach
    void setUp() {
        creature = new Creature("существо");
        creature.getBodyPartsMap().put("правая голова", new BodyPart("правая голова", 30, 20, BodyPartEnum.HEAD));
        creature.getBodyPartsMap().put("левая голова", new BodyPart("левая голова", 30, 20, BodyPartEnum.HEAD));
        creature.getBodyPartsMap().put("туловище", new BodyPart("туловище", 50, 20, BodyPartEnum.TORSO));
        creature.getBodyPartsMap().put("левая рука", new BodyPart("левая рука", 30, 5, BodyPartEnum.ARM));
        creature.getBodyPartsMap().put("правая рука", new BodyPart("правая рука", 30, 5, BodyPartEnum.ARM));
        creature.getBodyPartsMap().put("левая нога", new BodyPart("левая нога", 50, 10, BodyPartEnum.LEG));
        creature.getBodyPartsMap().put("правая нога", new BodyPart("правая нога", 50, 10, BodyPartEnum.LEG));
        human = new Human("человек");
    }

    @Test
    void testIllegalArgumentConstructor() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> new Creature(null)),
                () -> assertThrows(IllegalArgumentException.class, () -> new Creature(null, 100)),
                () -> assertThrows(IllegalArgumentException.class, () -> new Creature("")),
                () -> assertThrows(IllegalArgumentException.class, () -> new Creature("", 100)),
                () -> assertThrows(IllegalArgumentException.class, () -> new Creature("Test", 0)),
                () -> assertThrows(IllegalArgumentException.class, () -> new Creature("Test", -20)),
                () -> assertThrows(IllegalArgumentException.class, () -> new Human("Test", -20))
        );
    }

    @Test
    void testChangeCondition() {
        creature.changeCondition(CreatureConditionEnum.NERVOUS);
        assertEquals(CreatureConditionEnum.NERVOUS, creature.getCondition());
    }

    @Test
    void testGetWeight() {
        assertEquals(90, creature.getWeight());
        creature.getBodyPartsMap().remove("туловище");
        assertEquals(70, creature.getWeight());
    }

    @Test
    void testBodyPartMap() {
        assertEquals(7, creature.getBodyPartsMap().size());
        assertTrue(creature.getBodyPartsMap().containsKey("левая голова"));
        creature.getBodyPartsMap().remove("левая голова");
        assertFalse(creature.getBodyPartsMap().containsKey("левая голова"));
    }
    @Test
    void testBodyPartLines(){
        BodyPart bodyPart1 = new BodyPart("test1", 1, 1, BodyPartEnum.HEAD);
        assertEquals("test1", bodyPart1.getName());
        assertEquals(1, bodyPart1.getLength());
        assertEquals(1, bodyPart1.getWeight());
        assertEquals(BodyPartEnum.HEAD, bodyPart1.getBodyPartEnum());
        assertFalse(bodyPart1.isInAction());
        assertNull(bodyPart1.getInteractionBodyPart());
    }

    @Test
    void testHumanConstructorWithBodyParts() {
        assertEquals(6, human.getBodyPartsMap().size());
        Human human1 = new Human("test", 1, new BodyPart("test", 10, 10, BodyPartEnum.HEAD));
        assertEquals(1, human1.getBodyPartsMap().size());
    }


    @Test
    void testBodyPartsInteraction() {
        BodyPart bodyPart1 = new BodyPart("test1", 1, 1, BodyPartEnum.HEAD);
        BodyPart bodyPart2 = new BodyPart("test2", 1, 1, BodyPartEnum.HEAD);
        BodyPart bodyPart3 = new BodyPart("test3", 1, 1, BodyPartEnum.HEAD);
        BodyPart bodyPart4 = new BodyPart("test4", 1, 1, BodyPartEnum.HEAD);
        try {
            bodyPart1.interact(bodyPart2, "speak");
            assertTrue(bodyPart1.isInAction());
            assertTrue(bodyPart2.isInAction());
            assertEquals(bodyPart1, bodyPart2.getInteractionBodyPart());
            assertEquals(bodyPart2, bodyPart1.getInteractionBodyPart());
            assertThrows(AlreadyEngagedException.class, () -> bodyPart3.interact(bodyPart1, "test"));
            assertThrows(AlreadyEngagedException.class, () -> bodyPart1.interact(bodyPart3, "test"));
            assertThrows(NoInteractionException.class, () -> bodyPart3.endInteraction(bodyPart1));
            assertThrows(NoInteractionException.class, () -> bodyPart1.endInteraction(bodyPart3));
            bodyPart3.interact(bodyPart4, "test");
            assertThrows(NoInteractionException.class, () -> bodyPart3.endInteraction(bodyPart1));
            assertThrows(NoInteractionException.class, () -> bodyPart1.endInteraction(bodyPart3));
            bodyPart1.endInteraction(bodyPart2);
            assertFalse(bodyPart1.isInAction());
            assertFalse(bodyPart2.isInAction());
            assertNull(bodyPart2.getInteractionBodyPart());
            assertNull(bodyPart1.getInteractionBodyPart());
        } catch (AlreadyEngagedException | NoInteractionException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testHumanMeetings() {
        Human testNuman = new Human("test", 10);
        testNuman.meet(human);
        assertEquals(CreatureConditionEnum.NORMAL, testNuman.getCondition());
        testNuman.meet(creature);
        assertEquals(CreatureConditionEnum.SHOCKED, testNuman.getCondition());
    }
}