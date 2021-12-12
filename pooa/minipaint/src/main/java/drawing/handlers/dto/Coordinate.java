package drawing.handlers.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Coordinate {

    private final double x;
    private final double y;

    public static Coordinate at(final double x, final double y) {
        return new Coordinate(x, y);
    }

}
