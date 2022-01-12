package drawing.shapes.strategies;

import drawing.shapes.IShape;
import javafx.scene.shape.Path;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ArcStrategy implements EdgeStrategy {

    public static EdgeStrategy create() {
        return new ArcStrategy();
    }

    @Override
    public Path buildPath(final IShape from,
                          final IShape to) {
        System.out.println("Orthogonal line");
        return null;
    }
}
