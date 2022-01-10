package drawing.commands.shapes;

import drawing.commands.Command;
import drawing.commands.DeleteCommand;
import drawing.panes.DrawingPane;
import drawing.shapes.IShape;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ShapeDuplicateCommand implements Command {

    private final DrawingPane pane;
    private final List<IShape> duplicated;

    public static Command create(final DrawingPane pane) {
        return new ShapeDuplicateCommand(pane, new ArrayList<>());
    }

    @Override
    public void execute() {
        pane.getSelectedShapes()
                .forEach(shape -> {
                    final var duplicate = shape.duplicate();
                    pane.addShape(duplicate);
                    duplicated.add(duplicate);
                });
    }

    @Override
    public void undo() {
        duplicated.forEach(pane::addToSelected);
        DeleteCommand.create(pane)
                .execute();
        duplicated.clear();
    }

    @Override
    public Command duplicate() {
        return new ShapeDuplicateCommand(pane, duplicated);
    }
}
