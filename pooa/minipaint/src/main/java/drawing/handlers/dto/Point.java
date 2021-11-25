package drawing.handlers.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Point {

    private final double x;
    private final double y;

    public static Point at(final double x, final double y) {
        return new Point(x, y);
    }

}
