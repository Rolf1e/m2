package drawing.commands.shapes;

import drawing.commands.Command;
import drawing.panes.DrawingPane;
import drawing.shapes.IShape;
import drawing.shapes.decorators.TextDecorator;

public class ShapeTextCommand implements Command {

    private final DrawingPane pane;
    private final String text;

    private IShape oldShape;
    private IShape withText;

    public static Command create(final DrawingPane pane,
                                 final String text) {

        return new ShapeTextCommand(pane, text);
    }

    private ShapeTextCommand(final DrawingPane pane,
                             final String text) {
        this.pane = pane;
        this.text = text;
    }

    private ShapeTextCommand(final DrawingPane pane,
                             final String text,
                             final IShape oldShape,
                             final IShape withText) {
        this.pane = pane;
        this.text = text;
        this.oldShape = oldShape;
        this.withText = withText;
    }

    @Override
    public void execute() {
        oldShape = pane.getSelectedShapes()
                .get(0);

        pane.removeShape(oldShape);
        withText = TextDecorator.create(oldShape, text);
        pane.addShape(withText);
    }

    @Override
    public void undo() {
        pane.removeShape(withText);
        pane.addShape(oldShape);
    }

    @Override
    public Command duplicate() {
        return new ShapeTextCommand(pane, text, oldShape, withText);
    }

}
