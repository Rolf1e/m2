package drawing.bar.tool;

import drawing.handlers.buttons.ClearButtonHandler;
import drawing.handlers.buttons.DeleteButtonHandler;
import drawing.handlers.buttons.groups.UnGroupButtonHandler;
import drawing.handlers.buttons.shapes.EllipseButtonHandler;
import drawing.handlers.buttons.shapes.RectangleButtonHandler;
import drawing.handlers.buttons.shapes.TriangleButtonHandler;
import drawing.handlers.buttons.groups.GroupButtonHandler;
import drawing.panes.DrawingPane;
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
    UNGROUP("UnGroup", ActionEvent.ACTION);

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
                return ClearButtonHandler.create(drawingPane);
            case RECTANGLE:
                return RectangleButtonHandler.create(drawingPane);
            case CIRCLE:
                return EllipseButtonHandler.create(drawingPane);
            case TRIANGLE:
                return TriangleButtonHandler.create(drawingPane);
            case DELETE_SELECTION:
                return DeleteButtonHandler.create(drawingPane);
            case GROUP:
                return GroupButtonHandler.create(drawingPane);
            case UNGROUP:
                return UnGroupButtonHandler.create(drawingPane);
            default:
                throw new IllegalStateException("Invalid config " + this);
        }
    }

}
