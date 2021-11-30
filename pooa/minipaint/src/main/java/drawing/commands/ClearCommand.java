package drawing.commands;

import drawing.panes.DrawingPane;
import drawing.shapes.IShape;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ClearCommand implements Command {

    private final DrawingPane pane;
    private final List<IShape> shapes;

    public static Command create(final DrawingPane pane) {
        return new ClearCommand(pane, new ArrayList<>());
    }


    @Override
    public void execute() {
        shapes.addAll(pane.getShapes());
        pane.clear();
    }

    @Override
    public void undo() {
        pane.addShapes(shapes);
        shapes.clear();
    }
}
