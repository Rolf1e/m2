package drawing.commands.groups;

import drawing.commands.Command;
import drawing.panes.DrawingPane;
import drawing.shapes.adapter.GroupShapeAdapter;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class GroupCommand implements Command {

    private final DrawingPane pane;

    public static Command create(final DrawingPane pane) {
        return new GroupCommand(pane);
    }

    @Override
    public void execute() {
        final var selectedShapes = pane.getSelectedShapes();
        selectedShapes.forEach(pane::removeShape);
        pane.addShape(GroupShapeAdapter.create(selectedShapes));
    }

    @Override
    public void undo() {
        UnGroupCommand.create(pane)
                .execute();
    }

    @Override
    public Command duplicate() {
        return create(pane);
    }
}
