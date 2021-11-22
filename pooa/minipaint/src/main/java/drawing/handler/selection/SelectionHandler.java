package drawing.handler.selection;

import drawing.pane.DrawingPane;
import drawing.shape.IShape;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.StreamSupport;

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
        final var selected = StreamSupport.stream(drawingPane.spliterator(), false)
                .filter(shape -> shape.isOn(event.getX(), event.getY()))
                .findFirst();

        if (selected.isEmpty()) { // Clicked on nothing => we remove all
            removeAllFromSelected();
            return;
        }

        if (isSelected(selected.get())) {
            if (event.isShiftDown()) { // If already selected
                toggleSelection(selected.get());
            }
        } else {
            if (!event.isShiftDown()) {
                removeAllFromSelected();
            }
            addToSelected(selected.get());
        }

        System.out.println("Selected shapes: " + selectedShapes);
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
