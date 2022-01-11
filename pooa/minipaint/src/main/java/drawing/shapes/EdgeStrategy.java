package drawing.shapes;

import javafx.scene.shape.Path;

public interface EdgeStrategy {

    Path buildPath(final IShape from, final IShape to);
}
