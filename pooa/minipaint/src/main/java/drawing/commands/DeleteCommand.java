package drawing.commands;

import drawing.panes.DrawingPane;
import drawing.shapes.IShape;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class DeleteCommand implements Command {

    private final DrawingPane pane;
    private final List<IShape> selectedShapes;

    public static Command create(final DrawingPane pane) {
        return new DeleteCommand(pane, new ArrayList<>());
    }

    @Override
    public void execute() {
        pane.getSelectedShapes()
                .forEach(shape -> {
                    pane.removeShape(shape);
                    selectedShapes.add(shape);
                });
    }

    @Override
    public void undo() {
        final var selectionHandler = pane.getSelectionHandler();
        selectedShapes.forEach(shape -> {
            pane.addShape(shape);
            selectionHandler.toggleSelection(shape);
        });
    }
}
