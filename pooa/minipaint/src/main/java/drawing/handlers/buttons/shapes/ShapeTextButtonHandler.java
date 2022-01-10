package drawing.handlers.buttons.shapes;

import drawing.panes.DrawingPane;
import drawing.shapes.IShape;
import drawing.shapes.adapter.GroupShapeAdapter;
import drawing.shapes.decorators.TextDecorator;
import javafx.event.Event;
import javafx.event.EventHandler;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ShapeTextButtonHandler implements EventHandler<Event> {

    private final DrawingPane pane;



    @Override
    public void handle(final Event event) {
        final List<IShape> selectedShapes = pane.getSelectedShapes();
        pane.addShape(TextDecorator.create(GroupShapeAdapter.create(selectedShapes), "Text"));
        selectedShapes.forEach(pane::removeShape);
    }
}
