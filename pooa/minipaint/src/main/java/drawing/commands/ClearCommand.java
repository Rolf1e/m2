package drawing.commands;

import drawing.panes.DrawingPane;
import drawing.shapes.IShape;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ClearCommand implements Command {

    private final DrawingPane pane;
    private List<IShape> shapes;

    public static Command create(final DrawingPane pane) {
        return new ClearCommand(pane);
    }

    @Override
    public void execute() {
        shapes = List.copyOf(pane.getShapes());
        pane.clear();
    }

    @Override
    public void undo() {
        pane.addShapes(shapes);
    }

    @Override
    public Command duplicate() {
        return new ClearCommand(pane, shapes);
    }
}
