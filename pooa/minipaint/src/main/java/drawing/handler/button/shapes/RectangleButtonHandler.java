package drawing.handler.button.shapes;

import drawing.pane.DrawingPane;
import drawing.shape.IShape;
import drawing.shape.adapter.ShapeAdapter;
import javafx.scene.shape.Rectangle;

/**
 * Created by lewandowski on 20/12/2020.
 */
public class RectangleButtonHandler extends ShapeButtonHandler {

    public RectangleButtonHandler(DrawingPane drawingPane) {
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