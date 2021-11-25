package drawing.handlers.buttons.shapes;

import drawing.panes.DrawingPane;
import drawing.shapes.IShape;
import drawing.shapes.adapter.ShapeAdapter;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.shape.Rectangle;

/**
 * Created by lewandowski on 20/12/2020.
 */
public class RectangleButtonHandler extends ShapeButtonHandler {

    public static EventHandler<Event> create(final DrawingPane drawingPane) {
        return new RectangleButtonHandler(drawingPane);
    }

    private RectangleButtonHandler(DrawingPane drawingPane) {
        super(drawingPane);
    }

    @Override
    protected IShape createShape() {
        final var x = Math.min(originX, destinationX);
        final var y = Math.min(originY, destinationY);
        final var width = Math.abs(destinationX - originX);
        final var height = Math.abs(destinationY - originY);
        final var rectangle = new Rectangle(x, y, width, height);

        rectangle.getStyleClass()
                .add("rectangle");

        return ShapeAdapter.create(rectangle);
    }
}
