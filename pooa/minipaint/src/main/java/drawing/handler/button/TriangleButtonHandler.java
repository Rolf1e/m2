package drawing.handler.button;

import drawing.handler.dto.Point;
import drawing.handler.dto.Triangle;
import drawing.pane.DrawingPane;
import javafx.scene.shape.Shape;

public class TriangleButtonHandler extends ShapeButtonHandler {

    public TriangleButtonHandler(DrawingPane drawingPane) {
        super(drawingPane);
    }

    @Override
    protected Shape createShape() {

        final var triangle = Triangle.create(
                Point.at(originX, originY),
                Point.at(destinationX, destinationY),
                Point.at()

        );

        triangle.getStyleClass().add("triangle");
        return triangle;
    }

    private Point resolveLastPoint(final Point a,
                                   final Point b) {

    }
}
