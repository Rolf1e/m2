package drawing.handlers.mouse;

import drawing.commands.history.CommandHistory;
import drawing.commands.mouse.MouseMoveCommand;
import drawing.panes.DrawingPane;
import drawing.shapes.IShape;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class MouseMoveHandler implements EventHandler<MouseEvent> {

    private final DrawingPane pane;
    private final CommandHistory history;

    private double orgSceneX;
    private double orgSceneY;

    private double oldOffsetX;
    private double oldOffsetY;

    public MouseMoveHandler(final DrawingPane pane) {
        this.pane = pane;
        pane.addEventHandler(MouseEvent.MOUSE_PRESSED, this);
        pane.addEventHandler(MouseEvent.MOUSE_DRAGGED, this);
        pane.addEventHandler(MouseEvent.MOUSE_RELEASED, this);
        this.history = pane.getHistory();
    }

    @Override
    public void handle(final MouseEvent event) {
        if (MouseEvent.MOUSE_PRESSED.equals(event.getEventType())) {
            updateOrgSceneCoordinate(event);
            updateOldOffsetCoordinate(event);
        }

        if (MouseEvent.MOUSE_DRAGGED.equals(event.getEventType())) {
            translateWhenMouseDragged(event);
            updateOrgSceneCoordinate(event);
        }

        if (MouseEvent.MOUSE_RELEASED.equals(event.getEventType())) {
            // To avoid pilling command each time of the offset update we only remember the first
            executeCommandForHistory(event);
        }
    }

    private void updateOldOffsetCoordinate(MouseEvent event) {
        oldOffsetX = event.getSceneX();
        oldOffsetY = event.getSceneY();
    }

    private void executeCommandForHistory(final MouseEvent event) {
        pane.getSelectedShapes()
                .forEach(shape -> addToCommandHistory(event, shape));
    }

    private void addToCommandHistory(final MouseEvent event,
                                     final IShape shape) {
        history.execute(MouseMoveCommand.create(shape, oldOffsetX - event.getSceneX(), oldOffsetY - event.getSceneY()));
    }

    private void translateWhenMouseDragged(final MouseEvent event) {
        final var offsetX = event.getSceneX() - orgSceneX;
        final var offsetY = event.getSceneY() - orgSceneY;

        pane.getSelectedShapes()
                .forEach(shape -> updateOffSet(shape, offsetX, offsetY));

    }

    private void updateOrgSceneCoordinate(final MouseEvent event) {
        orgSceneX = event.getSceneX();
        orgSceneY = event.getSceneY();
    }

    private void updateOffSet(final IShape shape,
                              final double offsetX,
                              final double offsetY) {
        shape.offset(offsetX, offsetY);
    }

}
