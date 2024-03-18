package ifmo.block2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class HashMap {
    private static final int DEFAULT_CAPACITY = 12;
    private ArrayList<LinkedList<String>> hashArray;

    public HashMap() {
        this.hashArray = new ArrayList<>(DEFAULT_CAPACITY);
        for (int i = 0; i < DEFAULT_CAPACITY; i++) {
            hashArray.add(new LinkedList<>());
        }
    }

    private int hash(String key) {
        int hash = 0;
        for (int i = 0; i < key.length(); i++) {
            hash ^= key.charAt(i); // bitwise XOR operation for each character
        }
        return Math.abs(hash % DEFAULT_CAPACITY);
    }


    public void put(String key) {
        int index = hash(key);
        LinkedList<String> list = hashArray.get(index);
        list.add(key);
    }

    public void remove(String key) {
        int index = hash(key);
        LinkedList<String> list = hashArray.get(index);
        list.remove(key);
    }

    public boolean contains(String key) {
        int index = hash(key);
        LinkedList<String> list = hashArray.get(index);
        return list.contains(key);
    }

    public List<String> get(String key) {
        int index = hash(key);
        LinkedList<String> list = hashArray.get(index);
        return list;
    }

}
