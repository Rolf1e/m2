package drawing.handler.button.shapes;

import drawing.pane.DrawingPane;
import drawing.shape.IShape;
import drawing.shape.adapter.ShapeAdapter;
import javafx.scene.shape.Ellipse;

/**
 * Created by lewandowski on 20/12/2020.
 */
public class EllipseButtonHandler extends ShapeButtonHandler {

    public EllipseButtonHandler(DrawingPane drawingPane) {
        super(drawingPane);
    }

    @Override
    protected IShape createShape() {
        final var x = Math.min(originX, destinationX);
        final var y = Math.min(originY, destinationY);
        final var width = Math.abs(destinationX - originX);
        final var height = Math.abs(destinationY - originY);
        final var ellipse = new Ellipse(x + width / 2, y + height / 2, width / 2, height / 2);
        ellipse.getStyleClass()
                .add("ellipse");
        return ShapeAdapter.create(ellipse);
    }
}
