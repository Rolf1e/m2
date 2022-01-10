package drawing.panes;

import drawing.bar.status.observers.Observer;
import drawing.bar.status.observers.ObserverParameters;
import drawing.commands.history.CommandHistory;
import drawing.handlers.mouse.MouseMoveHandler;
import drawing.handlers.selection.SelectionHandler;
import drawing.shapes.IShape;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.shape.Rectangle;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class DrawingPane extends Pane implements Iterable<IShape> {

    private final MouseMoveHandler mouseMoveHandler;
    @Getter
    private final SelectionHandler selectionHandler;
    private final List<IShape> shapes;
    private final List<Observer> observers;
    @Getter
    private final CommandHistory history;

    public DrawingPane() {
        clipChildren();
        observers = new ArrayList<>();
        shapes = new ArrayList<>();
        history = CommandHistory.newCommandHistory(this);
        mouseMoveHandler = new MouseMoveHandler(this);
        selectionHandler = SelectionHandler.createAndRegisterEvents(this);
    }

    /**
     * Clips the children of this {@link Region} to its current size.
     * This requires attaching a change listener to the region’s layout bounds,
     * as JavaFX does not currently provide any built-in way to clip children.
     */
    void clipChildren() {
        final var outputClip = new Rectangle();
        this.setClip(outputClip);

        this.layoutBoundsProperty().addListener((ov, oldValue, newValue) -> {
            outputClip.setWidth(newValue.getWidth());
            outputClip.setHeight(newValue.getHeight());
        });
    }

    public void addObserver(final Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(final Observer observer) {
        observers.remove(observer);
    }

    public void addShapes(final List<IShape> shapes) {
        shapes.forEach(this::addShape);
    }

    public void addToSelected(final IShape shape) {
        selectionHandler.toggleSelection(shape);
    }

    public void addShape(final IShape shape) {
        shapes.add(shape);
        shape.addTo(this);
        final var parameters = getParameters();
        observers.forEach(observer -> observer.update(parameters));
    }

    public void removeShape(final IShape shape) {
        Objects.requireNonNull(shape);
        shapes.remove(shape);
        shape.removeFrom(this);
        selectionHandler.removeFromSelected(shape);
        updateObservers();
    }

    public void clear() {
        shapes.forEach(shape -> shape.removeFrom(this));
        shapes.clear();
        updateObservers();
    }

    public void updateObservers() {
        final var parameters = getParameters();
        observers.forEach(observer -> observer.update(parameters));
    }

    @Override
    public Iterator<IShape> iterator() {
        return shapes.iterator();
    }

    public Iterator<Observer> getObservers() {
        return observers.iterator();
    }

    public List<IShape> getShapes() {
        return shapes;
    }

    public List<IShape> getSelectedShapes() {
        return new ArrayList<>(selectionHandler.getSelectedShapes());
    }

    private ObserverParameters getParameters() {
        return ObserverParameters.getInstance()
                .setShapesSize(shapes.size())
                .setSelectedShapesSize(getSelectedShapes().size());
    }
}
