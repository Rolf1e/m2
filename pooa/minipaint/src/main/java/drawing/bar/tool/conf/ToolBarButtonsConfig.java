package drawing.bar.tool.conf;

import drawing.bar.tool.Style;
import drawing.commands.ClearCommand;
import drawing.commands.DeleteCommand;
import drawing.commands.groups.GroupCommand;
import drawing.commands.groups.LineCommand;
import drawing.commands.groups.UnGroupCommand;
import drawing.commands.shapes.ShapeDuplicateCommand;
import drawing.commands.shapes.ShapeTextCommand;
import drawing.commands.shapes.factory.ShapeType;
import drawing.handlers.buttons.ButtonHandler;
import drawing.handlers.buttons.RedoHandler;
import drawing.handlers.buttons.UndoHandler;
import drawing.handlers.buttons.shapes.ShapeButtonHandler;
import drawing.panes.DrawingPane;
import drawing.shapes.strategies.infra.EdgeStrategyHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import lombok.Getter;

@Getter
public enum ToolBarButtonsConfig {
    CLEAR("Clear", ActionEvent.ACTION),
    RECTANGLE("Rectangle", Style.create(Style.Type.ICON_ONLY, "icons/rectangle.png"), ActionEvent.ACTION),
    CIRCLE("Circle", Style.create(Style.Type.ICON_ONLY, "icons/circle.png"), ActionEvent.ACTION),
    TRIANGLE("Triangle", Style.create(Style.Type.ICON_ONLY, "icons/triangle.png"), ActionEvent.ACTION),
    DELETE_SELECTION("Delete selection", Style.create(Style.Type.ICON_ONLY, "icons/delete.png"), ActionEvent.ACTION),
    GROUP("Group", Style.create(Style.Type.ICON_ONLY, "icons/group.png"), ActionEvent.ACTION),
    UNGROUP("UnGroup", ActionEvent.ACTION),
    UNDO("Undo", ActionEvent.ACTION),
    REDO("Redo", ActionEvent.ACTION),
    DUPLICATE("Duplicate", Style.create(Style.Type.ICON_ONLY, "icons/duplicate.png"), ActionEvent.ACTION),
    ADD_TEXT("Add text", ActionEvent.ACTION),
    LINK_SHAPES("Links 2 shpaes", ActionEvent.ACTION);

    private final String name;
    private final Style style;
    private final EventType<ActionEvent> event;

    ToolBarButtonsConfig(String name, EventType<ActionEvent> event) {
        this.name = name;
        this.style = Style.create(Style.Type.TEXT_ONLY, "");
        this.event = event;
    }

    ToolBarButtonsConfig(String name, Style style, EventType<ActionEvent> event) {
        this.style = style;
        this.name = name;
        this.event = event;
    }

    public final EventHandler<? super ActionEvent> getHandler(final DrawingPane drawingPane) {
        switch (this) {
            case CLEAR:
                return ButtonHandler.create(ClearCommand.create(drawingPane), drawingPane.getHistory());
            case RECTANGLE:
                return ShapeButtonHandler.create(drawingPane, ShapeType.RECTANGLE);
            case CIRCLE:
                return ShapeButtonHandler.create(drawingPane, ShapeType.ELLIPSE);
            case TRIANGLE:
                return ShapeButtonHandler.create(drawingPane, ShapeType.TRIANGLE);
            case DELETE_SELECTION:
                return ButtonHandler.create(DeleteCommand.create(drawingPane), drawingPane.getHistory());
            case GROUP:
                return ButtonHandler.create(GroupCommand.create(drawingPane), drawingPane.getHistory());
            case UNGROUP:
                return ButtonHandler.create(UnGroupCommand.create(drawingPane), drawingPane.getHistory());
            case UNDO:
                return UndoHandler.create(drawingPane.getHistory());
            case REDO:
                return RedoHandler.create(drawingPane.getHistory());
            case DUPLICATE:
                return ButtonHandler.create(ShapeDuplicateCommand.create(drawingPane), drawingPane.getHistory());
            case ADD_TEXT:
                return ButtonHandler.create(ShapeTextCommand.create(drawingPane, "TEXT"), drawingPane.getHistory());
            case LINK_SHAPES:
                return ButtonHandler.create(LineCommand.create(drawingPane, EdgeStrategyHandler.getInstance()), drawingPane.getHistory());
            default:
                throw new IllegalStateException("Invalid config " + this);
        }
    }

}
