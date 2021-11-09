package drawing.handler.mouse;

import drawing.pane.DrawingPane;
import drawing.shape.IShape;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * Created by lewandowski on 20/12/2020.
 */
public class MouseMoveHandler implements EventHandler<MouseEvent> {

    private final DrawingPane drawingPane;

    private double orgSceneX;
    private double orgSceneY;

    public MouseMoveHandler(final DrawingPane drawingPane) {
        this.drawingPane = drawingPane;
        drawingPane.addEventHandler(MouseEvent.MOUSE_PRESSED, this);
        drawingPane.addEventHandler(MouseEvent.MOUSE_DRAGGED, this);
        drawingPane.addEventHandler(MouseEvent.MOUSE_RELEASED, this);
    }

    @Override
    public void handle(final MouseEvent event) {
        if (MouseEvent.MOUSE_PRESSED.equals(event.getEventType())) {
            updateOrgSceneCoordinate(event);
        }

        if (MouseEvent.MOUSE_DRAGGED.equals(event.getEventType())) {
            translateWhenMouseDragged(event);
        }

        if (MouseEvent.MOUSE_RELEASED.equals(event.getEventType())) {
            translateWhenMouseReleased(event);
        }
    }


    private void translateWhenMouseDragged(final MouseEvent event) {
        drawingPane.getSelectedShapes()
                .forEach(shape -> {
                            updateOffSet(event, shape);
                            updateOrgSceneCoordinate(event);
                        }
                );
    }

    private void translateWhenMouseReleased(final MouseEvent event) {
        drawingPane.getSelectedShapes()
                .forEach(shape -> updateOffSet(event, shape));
    }

    private void updateOrgSceneCoordinate(final MouseEvent event) {
        orgSceneX = event.getSceneX();
        orgSceneY = event.getSceneY();
    }

    private void updateOffSet(final MouseEvent event,
                              final IShape shape) {

        final var offsetX = event.getSceneX() - orgSceneX;
        final var offsetY = event.getSceneY() - orgSceneY;
        shape.offset(offsetX, offsetY);
    }
}
