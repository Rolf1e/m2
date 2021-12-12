package drawing.handlers.mouse;

import drawing.commands.Command;
import drawing.commands.MouseMoveCommand;
import drawing.commands.history.CommandHistory;
import drawing.handlers.dto.Coordinate;
import drawing.panes.DrawingPane;
import drawing.shapes.IShape;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class MouseMoveHandler implements EventHandler<MouseEvent> {

    private final DrawingPane pane;
    private final CommandHistory history;

    private double orgSceneX;
    private double orgSceneY;

    private double initalX;
    private double intialY;

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
            updateInitialCoordinate(event); // To avoid pilling command each time of the offset update we only remember the first
        }

        if (MouseEvent.MOUSE_DRAGGED.equals(event.getEventType())) {
            translateWhenMouseDragged(event);
            updateOrgSceneCoordinate(event);
        }

        if (MouseEvent.MOUSE_RELEASED.equals(event.getEventType())) {
            executeCommandForHistory(event); // We use Command pattern to use history
        }
    }

    private void updateInitialCoordinate(MouseEvent event) {
        initalX = event.getSceneX();
        intialY = event.getSceneY();
    }

    private void executeCommandForHistory(final MouseEvent event) {
        pane.getSelectedShapes()
                .forEach(shape -> addToCommandHistory(event, shape));
    }

    private void addToCommandHistory(final MouseEvent event,
                                     final IShape shape) {

        final Coordinate offsets = computeOffSet(event);
        final Command command = MouseMoveCommand.create(
                shape,
                offsets,
                Coordinate.at(initalX - event.getSceneX(), intialY - event.getSceneY())
        );
        history.execute(command);
    }

    private void translateWhenMouseDragged(final MouseEvent event) {
        final Coordinate offsets = computeOffSet(event);
        pane.getSelectedShapes()
                .forEach(shape -> MouseMoveCommand.updateOffSet(shape, offsets));

    }

    private void updateOrgSceneCoordinate(final MouseEvent event) {
        orgSceneX = event.getSceneX();
        orgSceneY = event.getSceneY();
    }

    public Coordinate computeOffSet(final MouseEvent event) {
        return Coordinate.at(event.getSceneX() - orgSceneX, event.getSceneY() - orgSceneY);
    }

}
