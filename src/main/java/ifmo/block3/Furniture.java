package ifmo.block3;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class Furniture {
    private final String name;
    private final int width;
    private final int length;
    private final int height;
}
