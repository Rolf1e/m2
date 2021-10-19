package drawing.handler.mouse;

import drawing.pane.DrawingPane;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * Created by lewandowski on 20/12/2020.
 */
public class MouseMoveHandler implements EventHandler<MouseEvent> {

    private final DrawingPane drawingPane;

    private double orgSceneX;
    private double orgSceneY;

    public MouseMoveHandler(DrawingPane drawingPane) {
        this.drawingPane = drawingPane;
        drawingPane.addEventHandler(MouseEvent.MOUSE_PRESSED, this);
        drawingPane.addEventHandler(MouseEvent.MOUSE_DRAGGED, this);
        drawingPane.addEventHandler(MouseEvent.MOUSE_RELEASED, this);
    }

    @Override
    public void handle(MouseEvent event) {

        if (MouseEvent.MOUSE_PRESSED.equals(event.getEventType())) {
            orgSceneX = event.getSceneX();
            orgSceneY = event.getSceneY();

            for (final var shape : drawingPane) {
                if (shape.isOn(event.getX(), event.getY())) {
                    shape.setSelected(true);
                    break;
                }
            }
        }

        if (MouseEvent.MOUSE_DRAGGED.equals(event.getEventType())) {
            for (final var shape : drawingPane) {
                if (shape.isSelected()) {
                    shape.setSelected(true);
                    double offsetX = event.getSceneX() - orgSceneX;
                    double offsetY = event.getSceneY() - orgSceneY;
                    shape.offset(offsetX, offsetY);
                    orgSceneX = event.getSceneX();
                    orgSceneY = event.getSceneY();
                }
            }
        }

        if (MouseEvent.MOUSE_RELEASED.equals(event.getEventType())) {
            translateFigure(event);
        }
    }

    private void translateFigure(MouseEvent event) {
        for (final var shape : drawingPane) {
            if (shape.isSelected()) {
                shape.setSelected(false);
                double offsetX = event.getSceneX() - orgSceneX;
                double offsetY = event.getSceneY() - orgSceneY;
                shape.offset(offsetX, offsetY);
            }
        }
    }
}
