package drawing.handler.selection;

import drawing.pane.DrawingPane;
import drawing.shape.IShape;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SelectionHandler implements EventHandler<MouseEvent> {

    private final DrawingPane drawingPane;
    private List<IShape> selectedShapes;

    public static SelectionHandler createAndRegisterEvents(final DrawingPane pane) {
        final var selectionHandler = new SelectionHandler(pane, new ArrayList<>());
        pane.addEventHandler(MouseEvent.MOUSE_PRESSED, selectionHandler);
        pane.addEventHandler(MouseEvent.MOUSE_DRAGGED, selectionHandler);
        pane.addEventHandler(MouseEvent.MOUSE_RELEASED, selectionHandler);
        return selectionHandler;
    }

    private SelectionHandler(final DrawingPane drawingPane,
                             final List<IShape> selectedShapes) {
        this.drawingPane = drawingPane;
        this.selectedShapes = selectedShapes;
    }

    @Override
    public void handle(final MouseEvent event) {
        if (MouseEvent.MOUSE_PRESSED.equals(event.getEventType())) {
            for (final var shape : drawingPane) {
                if (shape.isOn(event.getX(), event.getY())) {
                    if (event.isShiftDown()) {
                        shape.setSelected(true);
                        selectedShapes.add(shape);
                    } else {
                        shape.setSelected(true);
                        selectedShapes = Arrays.asList(shape);
                    }
                }
            }


            if (MouseEvent.MOUSE_DRAGGED.equals(event.getEventType())) {
            }

            if (MouseEvent.MOUSE_RELEASED.equals(event.getEventType())) {
            }

        }


    }
