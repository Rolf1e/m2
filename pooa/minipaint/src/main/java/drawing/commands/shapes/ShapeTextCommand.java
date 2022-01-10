package drawing.commands.shapes;

import drawing.commands.Command;
import drawing.panes.DrawingPane;
import drawing.shapes.IShape;
import drawing.shapes.decorators.TextDecorator;

import java.util.ArrayList;
import java.util.List;

public class ShapeTextCommand implements Command {

    private final DrawingPane pane;
    private final String text;
    private final List<IShape> oldShapes;
    private final List<IShape> shapesWithText;

    public static Command create(final DrawingPane pane,
                                 final String text) {

        return new ShapeTextCommand(pane, text, new ArrayList<>(), new ArrayList<>());
    }

    private ShapeTextCommand(final DrawingPane pane,
                             final String text,
                             final List<IShape> oldShapes,
                             final List<IShape> shapesWithText) {
        this.pane = pane;
        this.text = text;
        this.oldShapes = oldShapes;
        this.shapesWithText = shapesWithText;
    }

    @Override
    public void execute() {
        oldShapes.clear();
        oldShapes.addAll(pane.getSelectedShapes());

        oldShapes.forEach(shape -> {
            pane.removeShape(shape);
            final var withText = TextDecorator.from(shape, text);
            shapesWithText.add(withText);
            pane.addShape(withText);
        });

    }

    @Override
    public void undo() {
        shapesWithText.forEach(pane::removeShape);
        shapesWithText.clear();
        oldShapes.forEach(pane::addShape);
    }

    @Override
    public Command duplicate() {
        return new ShapeTextCommand(pane, text, oldShapes, shapesWithText);
    }

}
