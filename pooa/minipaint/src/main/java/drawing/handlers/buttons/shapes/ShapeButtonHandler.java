package drawing.handlers.buttons.shapes;

import drawing.panes.DrawingPane;
import drawing.shapes.IShape;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * Created by lewandowski on 20/12/2020.
 */
public abstract class ShapeButtonHandler implements EventHandler<Event> {

    protected double originX;
    protected double originY;
    protected double destinationX;
    protected double destinationY;

    private final DrawingPane drawingPane;

    public ShapeButtonHandler(final DrawingPane drawingPane) {
        this.drawingPane = drawingPane;
    }

    @Override
    public void handle(final Event event) {

        if (event instanceof ActionEvent) {
            drawingPane.addEventHandler(MouseEvent.MOUSE_PRESSED, this);
        }

        if (event instanceof MouseEvent) {
            if (MouseEvent.MOUSE_PRESSED.equals(event.getEventType())) {
                drawingPane.addEventHandler(MouseEvent.MOUSE_RELEASED, this);
                originX = ((MouseEvent) event).getX();
                originY = ((MouseEvent) event).getY();
            }

            if (MouseEvent.MOUSE_RELEASED.equals(event.getEventType())) {
                destinationX = ((MouseEvent) event).getX();
                destinationY = ((MouseEvent) event).getY();

                drawingPane.addShape(createShape());

                drawingPane.removeEventHandler(MouseEvent.MOUSE_PRESSED, this);
                drawingPane.removeEventHandler(MouseEvent.MOUSE_RELEASED, this);
            }
        }
    }

    protected abstract IShape createShape();

}
