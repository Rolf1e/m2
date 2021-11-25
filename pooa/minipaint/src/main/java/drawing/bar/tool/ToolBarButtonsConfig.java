package drawing.bar.tool;

import drawing.handlers.buttons.ClearButtonHandler;
import drawing.handlers.buttons.DeleteButtonHandler;
import drawing.handlers.buttons.shapes.EllipseButtonHandler;
import drawing.handlers.buttons.shapes.RectangleButtonHandler;
import drawing.handlers.buttons.shapes.TriangleButtonHandler;
import drawing.panes.DrawingPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum ToolBarButtonsConfig {
    CLEAR(Style.create(Style.Type.TEXT_ONLY, "Clear"), ActionEvent.ACTION),
    RECTANGLE(Style.create(Style.Type.TEXT_ONLY, "Rectangle"), ActionEvent.ACTION),
    CIRCLE(Style.create(Style.Type.TEXT_ONLY, "Circle"), ActionEvent.ACTION),
    TRIANGLE(Style.create(Style.Type.TEXT_ONLY, "Triangle"), ActionEvent.ACTION),
    DELETE_SELECTION(Style.create(Style.Type.ICON_ONLY, "icons/baseline_delete_black_24dp.png"), ActionEvent.ACTION);

    private final Style style;
    private final EventType<ActionEvent> event;

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
            default:
                throw new IllegalStateException("Invalid config");
        }
    }

}
