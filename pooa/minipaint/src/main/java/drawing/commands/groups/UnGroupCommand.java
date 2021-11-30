package drawing.commands.groups;

import drawing.commands.Command;
import drawing.panes.DrawingPane;
import drawing.shapes.IShape;
import drawing.shapes.adapter.GroupShapeAdapter;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class UnGroupCommand implements Command {

    private final DrawingPane pane;

    public static Command create(final DrawingPane pane) {
        return new UnGroupCommand(pane);
    }

    @Override
    public void execute() {
        final var selectedShapes = pane.getSelectedShapes();
        selectedShapes.forEach(shape -> {
            if (shape instanceof GroupShapeAdapter) {
                pane.removeShape(shape);
                final List<IShape> group = ((GroupShapeAdapter) shape).getGroup();
                group.forEach(pane::addShape);
            }
        });
    }

    @Override
    public void undo() {

    }
}
