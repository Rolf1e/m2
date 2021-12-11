package drawing.commands.shapes.factory;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ShapeParameters {

    private final double originX;
    private final double originY;
    private final double destinationX;
    private final double destinationY;

    public static ShapeParameters create(final double originX,
                                         final double originY,
                                         final double destinationX,
                                         final double destinationY) {
        return new ShapeParameters(originX, originY, destinationX, destinationY);
    }
}
