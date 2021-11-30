package drawing.handlers.buttons.groups;

import drawing.panes.DrawingPane;
import drawing.shapes.adapter.GroupShapeAdapter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class GroupButtonHandler implements EventHandler<ActionEvent> {

    private final DrawingPane pane;

    public static GroupButtonHandler create(final DrawingPane drawingPane) {
        return new GroupButtonHandler(drawingPane);
    }

    private GroupButtonHandler(final DrawingPane pane) {
        this.pane = pane;
    }

    @Override
    public void handle(final ActionEvent event) {
        final var selectedShapes = pane.getSelectedShapes();
        selectedShapes.forEach(pane::removeShape);
        pane.addShape(GroupShapeAdapter.create(selectedShapes));
    }
}
