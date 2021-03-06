package drawing.commands.groups;

import drawing.commands.Command;
import drawing.panes.DrawingPane;
import drawing.shapes.IShape;
import drawing.shapes.adapter.GroupShapeAdapter;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class UnGroupCommand implements Command {

    private final DrawingPane pane;

    private final List<IShape> oldSelectedShapes;

    public static Command create(final DrawingPane pane) {
        return new UnGroupCommand(pane, new ArrayList<>());
    }

    @Override
    public void execute() {
        final var selectedShapes = pane.getSelectedShapes();
        selectedShapes.forEach(shape -> {
            if (shape instanceof GroupShapeAdapter) {
                pane.removeShape(shape);
                final List<IShape> group = ((GroupShapeAdapter) shape).getGroup();
                group.forEach(pane::addShape);
                oldSelectedShapes.add(shape);
            }
        });
    }

    @Override
    public void undo() {
        oldSelectedShapes.forEach(pane::addToSelected);
        GroupCommand.create(pane)
                .execute();
    }

    @Override
    public Command duplicate() {
        return create(pane);
    }
}
