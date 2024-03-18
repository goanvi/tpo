package ifmo.block3;

import lombok.Getter;

@Getter
public abstract class Furniture {
    private final String name;
    private final int width;
    private final int length;
    private final int height;

    public Furniture(String name, int width, int length, int height) {
        if (name == null || name.isEmpty() || name.isBlank() || width <= 0 || length <= 0 || height <= 0) {
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.width = width;
        this.length = length;
        this.height = height;
    }
}
