package drawing.shapes.strategies;

import drawing.shapes.IShape;
import javafx.scene.shape.Path;

public interface EdgeStrategy {

    Path buildPath(final IShape from, final IShape to);
}
