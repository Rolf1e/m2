package drawing.handler.selection;

import drawing.pane.DrawingPane;
import drawing.shape.IShape;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

public class SelectionHandler implements EventHandler<MouseEvent> {

    private final DrawingPane drawingPane;
    @Getter
    private final Set<IShape> selectedShapes;

    public static SelectionHandler createAndRegisterEvents(final DrawingPane pane) {
        final var selectionHandler = new SelectionHandler(pane, new HashSet<>());
        pane.addEventHandler(MouseEvent.MOUSE_PRESSED, selectionHandler);
        return selectionHandler;
    }

    /**
     * A {@link Set} guarantee we do not have the same shape two times
     */
    private SelectionHandler(final DrawingPane drawingPane,
                             final Set<IShape> selectedShapes) {
        this.drawingPane = drawingPane;
        this.selectedShapes = selectedShapes;
    }

    @Override
    public void handle(final MouseEvent event) {
        if (MouseEvent.MOUSE_PRESSED.equals(event.getEventType())) {
            selectShapes(event);
        }

        System.out.println("Selected shapes: " + selectedShapes);
    }

    private void selectShapes(final MouseEvent event) {
        for (final var shape : drawingPane) {
            selectShapes(event, shape);
        }
    }

    private void selectShapes(final MouseEvent event,
                              final IShape shape) {

        if (shape.isOn(event.getX(), event.getY())) {
            selectShapesWhenUnderCursor(event, shape);
        } else {
            removeAllFromSelected();
        }
    }

    private void selectShapesWhenUnderCursor(final MouseEvent event,
                                             final IShape shape) {

        if (!event.isShiftDown()) { // Shift is not pressed
            removeAllFromSelected();
        }

        toggleSelection(shape);
    }

    private void toggleSelection(final IShape shape) {
        if (isSelected(shape)) {
            removeFromSelected(shape);
        } else {
            addToSelected(shape);
        }
    }

    private boolean isSelected(final IShape shape) {
        return selectedShapes.contains(shape);
    }

    private void addToSelected(final IShape shape) {
        selectedShapes.add(shape);
        shape.setSelected(true);
    }

    private void removeFromSelected(final IShape shape) {
        selectedShapes.remove(shape);
        shape.setSelected(false);
    }

    private void removeAllFromSelected() {
        System.out.println("Remove all !");
        selectedShapes.forEach(shape -> shape.setSelected(false));
        selectedShapes.clear();
    }

}
