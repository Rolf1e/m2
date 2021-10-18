package drawing.handler.button;

import drawing.handler.dto.Point;
import drawing.handler.dto.Triangle;
import drawing.pane.DrawingPane;
import javafx.scene.shape.Shape;

public class TriangleButtonHandler extends ShapeButtonHandler {

    public static TriangleButtonHandler create(final DrawingPane drawingPane) {
        return new TriangleButtonHandler(drawingPane);
    }

    private TriangleButtonHandler(final DrawingPane drawingPane) {
        super(drawingPane);
    }

    @Override
    protected Shape createShape() {
        final Point a = Point.at(originX, originY);
        final Point b = Point.at(destinationX, destinationY);
        final var triangle = Triangle.create(a, b, resolveLastPoint(a, b));

        triangle.getStyleClass().add("triangle");
        return triangle;
    }

    private Point resolveLastPoint(final Point a, final Point b) {
        return Point.at(a.getX() - b.getX() / 2, a.getY() - b.getY() / 2);
    }


}
