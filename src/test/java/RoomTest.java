import ifmo.block3.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class RoomTest {
    Room room;
    Chair chair;
    ControlPanel controlPanel;

    @BeforeEach
    void setUp() {
        room = new Room("room", 100, 100, 100);
        chair = new Chair("chair", 1, 1, 1);
        controlPanel = new ControlPanel("control panel", 1, 1, 1);
    }

    @Test
    void testRoomConstructor() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> new Room(null, 100, 100, 100)),
                () -> assertThrows(IllegalArgumentException.class, () -> new Room("", 100, 100, 100)),
                () -> assertThrows(IllegalArgumentException.class, () -> new Room("   ", 100, 100, 100)),
                () -> assertThrows(IllegalArgumentException.class, () -> new Room("test", -10, 100, 100)),
                () -> assertThrows(IllegalArgumentException.class, () -> new Room("test", 100, -10, 100)),
                () -> assertThrows(IllegalArgumentException.class, () -> new Room("test", 100, 100, -10))
        );
        assertEquals(100, room.getHeight());
        assertEquals(100, room.getWidth());
        assertEquals(100, room.getLength());
        assertEquals("room", room.getName());
    }

    @Test
    void testFurnitureConstructor() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> new Chair(null, 100, 100, 100)),
                () -> assertThrows(IllegalArgumentException.class, () -> new Chair("", 100, 100, 100)),
                () -> assertThrows(IllegalArgumentException.class, () -> new Chair("   ", 100, 100, 100)),
                () -> assertThrows(IllegalArgumentException.class, () -> new Chair("test", -10, 100, 100)),
                () -> assertThrows(IllegalArgumentException.class, () -> new Chair("test", 100, -10, 100)),
                () -> assertThrows(IllegalArgumentException.class, () -> new Chair("test", 100, 100, -10))
        );
    }

    @Test
    void testAddingFurnitureInRoom() {
        room.addFurniture(chair);
        room.addFurniture(controlPanel);
        assertEquals(2, room.getFurnitureMap().size());
        assertEquals(chair, room.getFurniture("chair"));
        room.getFurnitureMap().remove("chair");
        assertEquals(1, room.getFurnitureMap().size());
        Chair testChair1 = new Chair("test", 1000,10,10);
        Chair testChair2 = new Chair("test", 10,1000,10);
        Chair testChair3 = new Chair("test", 10,10,1000);
        assertFalse(room.addFurniture(testChair1));
        assertFalse(room.addFurniture(testChair2));
        assertFalse(room.addFurniture(testChair3));
        assertFalse(room.addFurniture(null));
    }

    @Test
    void testOccupyingChair() {
        Human human = new Human("test");
        chair.setOccupant(human);
        assertTrue(chair.isOccupied());
        assertEquals(human, chair.getOccupant());
        chair.resetOccupant();
        assertFalse(chair.isOccupied());
    }

    @Test
    void testEnteringCreaturesInRoom() {
        Human human1 = new Human("human1");
        Human human2 = new Human("human2");
        Room testRoom = new Room("testRoom", 100, 100, 100);
        room.enterRoom(human1);
        room.enterRoom(human2);
        assertEquals(2, room.getCreatureMap().size());
        assertEquals(human1, room.exitRoom("human1"));
        assertEquals(1, room.getCreatureMap().size());
        human1.goTo(testRoom);
        assertEquals(1, testRoom.getCreatureMap().size());
        human1.goTo(testRoom, room);
        assertEquals(2, room.getCreatureMap().size());
        Creature creature = new Creature("test", 1000);
        assertFalse(room.enterRoom(creature));
        assertFalse(room.enterRoom(null));

    }

    @Test
    void testGoTo(){
        Human human1 = new Human("human1");
        Room testRoom = new Room("testRoom", 100, 100, 100);
        human1.goTo(testRoom);
        human1.goTo(null, null);
        assertEquals(1, testRoom.getCreatureMap().size());
        human1.goTo(testRoom, null);
        assertTrue(testRoom.getCreatureMap().isEmpty());
    }


}
