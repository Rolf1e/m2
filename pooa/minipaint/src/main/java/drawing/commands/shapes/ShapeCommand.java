package drawing.commands.shapes;

import drawing.commands.Command;
import drawing.commands.shapes.factory.ShapeFactory;
import drawing.commands.shapes.factory.ShapeParameters;
import drawing.commands.shapes.factory.ShapeType;
import drawing.panes.DrawingPane;
import drawing.shapes.IShape;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ShapeCommand implements Command {

    private final DrawingPane pane;
    private final IShape createdShape;

    public static Command from(final DrawingPane pane,
                               final ShapeFactory shapeFactory,
                               final ShapeType type,
                               final ShapeParameters parameters) {

        final IShape shape = shapeFactory.newShape(type, parameters);
        return new ShapeCommand(pane, shape);
    }

    @Override
    public void execute() {
        pane.addShape(createdShape);
    }

    @Override
    public void undo() {
        pane.removeShape(createdShape);
    }

    @Override
    public Command duplicate() {
        return new ShapeCommand(pane, createdShape);
    }
}
