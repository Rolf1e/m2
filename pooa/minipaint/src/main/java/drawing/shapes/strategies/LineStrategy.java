package drawing.shapes.strategies;

import drawing.shapes.EdgeStrategy;
import drawing.shapes.IShape;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class LineStrategy implements EdgeStrategy {

    public static EdgeStrategy create() {
        return new LineStrategy();
    }

    @Override
    public Path buildPath(final IShape from, final IShape to) {
        final var moveTo = new MoveTo();
        final var lineToStart = new LineTo();

        moveTo.xProperty().bind(from.translateCenterX());
        moveTo.yProperty().bind(from.translateCenterY());

        lineToStart.xProperty().bind(to.translateCenterX());
        lineToStart.yProperty().bind(to.translateCenterY());

        return new Path(moveTo, lineToStart);
    }
}
