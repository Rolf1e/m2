package drawing.handler.button.shapes;

import drawing.handler.dto.Point;
import drawing.handler.dto.Triangle;
import drawing.pane.DrawingPane;
import drawing.shape.IShape;
import drawing.shape.adapter.ShapeAdapter;

public class TriangleButtonHandler extends ShapeButtonHandler {

    public static TriangleButtonHandler create(final DrawingPane drawingPane) {
        return new TriangleButtonHandler(drawingPane);
    }

    private TriangleButtonHandler(final DrawingPane drawingPane) {
        super(drawingPane);
    }

    @Override
    protected IShape createShape() {
        final var a = Point.at(originX, originY);
        final var b = Point.at(destinationX, destinationY);
        final var triangle = Triangle.create(a, b, resolveLastPoint(a, b));

        triangle.getStyleClass()
                .add("triangle");
        return ShapeAdapter.create(triangle);
    }

    private Point resolveLastPoint(final Point a, final Point b) {
        return Point.at(a.getX() - b.getX() / 2, a.getY() - b.getY() / 2);
    }


}
