package drawing.handlers.buttons.shapes;

import drawing.commands.shapes.ShapeCommand;
import drawing.commands.shapes.factory.ShapeFactory;
import drawing.commands.shapes.factory.ShapeParameters;
import drawing.commands.shapes.factory.ShapeType;
import drawing.panes.DrawingPane;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ShapeButtonHandler implements EventHandler<Event> {

    private final DrawingPane pane;
    private final ShapeFactory shapeFactory;
    private final ShapeType type;

    private double originX;
    private double originY;

    public static EventHandler<Event> create(final DrawingPane pane,
                                             final ShapeType type) {
        return new ShapeButtonHandler(pane, ShapeFactory.create(), type);
    }

    @Override
    public void handle(final Event event) {

        if (event instanceof ActionEvent) {
            pane.addEventHandler(MouseEvent.MOUSE_PRESSED, this);
        }

        if (event instanceof MouseEvent) {
            if (MouseEvent.MOUSE_PRESSED.equals(event.getEventType())) {
                pane.addEventHandler(MouseEvent.MOUSE_RELEASED, this);
                originX = ((MouseEvent) event).getX();
                originY = ((MouseEvent) event).getY();
            }

            if (MouseEvent.MOUSE_RELEASED.equals(event.getEventType())) {
                final var destinationX = ((MouseEvent) event).getX();
                final var destinationY = ((MouseEvent) event).getY();

                final var command = ShapeCommand.from(
                        pane,
                        shapeFactory,
                        type,
                        ShapeParameters.create(originX, originY, destinationX, destinationY)
                );
                command.execute(); // create and add the shape to the pane

                pane.removeEventHandler(MouseEvent.MOUSE_PRESSED, this);
                pane.removeEventHandler(MouseEvent.MOUSE_RELEASED, this);
            }
        }
    }
}
