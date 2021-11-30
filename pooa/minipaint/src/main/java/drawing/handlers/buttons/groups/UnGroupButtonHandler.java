package drawing.handlers.buttons.groups;

import drawing.panes.DrawingPane;
import drawing.shapes.IShape;
import drawing.shapes.adapter.GroupShapeAdapter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.List;

public class UnGroupButtonHandler implements EventHandler<ActionEvent> {

    private final DrawingPane pane;

    public static UnGroupButtonHandler create(final DrawingPane drawingPane) {
        return new UnGroupButtonHandler(drawingPane);
    }

    private UnGroupButtonHandler(final DrawingPane pane) {
        this.pane = pane;
    }

    @Override
    public void handle(final ActionEvent event) {
        final var selectedShapes = pane.getSelectedShapes();
        selectedShapes.forEach(shape -> {
            if (shape instanceof GroupShapeAdapter) {
                pane.removeShape(shape);
                final List<IShape> group = ((GroupShapeAdapter) shape).getGroup();
                group.forEach(pane::addShape);
            }
        });
    }
}
