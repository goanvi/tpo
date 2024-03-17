package ifmo.block2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class HashMapTest {
    private HashMap map;

    @BeforeEach
    public void setUp() {
        map = new HashMap();
    }

    @Test
    public void testPutAndGet() {
        map.put("apple");
        map.put("banana");
        map.put("orange");

        assertTrue(map.contains("apple"));
        assertTrue(map.contains("banana"));
        assertTrue(map.contains("orange"));
        assertFalse(map.contains("grape"));

        List<String> list = map.get("apple");
        assertNotNull(list);
        assertTrue(list.contains("apple"));
    }

    @Test
    public void testRemove() {
        map.put("apple");
        map.put("banana");
        map.put("orange");

        assertTrue(map.contains("banana"));
        map.remove("banana");
        assertFalse(map.contains("banana"));
    }

    @Test
    public void testEmptyMap() {
        assertFalse(map.contains("apple"));
        assertFalse(map.contains("banana"));
        assertFalse(map.contains("orange"));
    }
}
