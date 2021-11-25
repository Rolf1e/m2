package drawing.handlers.bar;

import drawing.handlers.buttons.ClearButtonHandler;
import drawing.handlers.buttons.DeleteButtonHandler;
import drawing.handlers.buttons.shapes.EllipseButtonHandler;
import drawing.handlers.buttons.shapes.RectangleButtonHandler;
import drawing.handlers.buttons.shapes.TriangleButtonHandler;
import drawing.panes.DrawingPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.control.Button;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ToolBar {

    @Getter
    private final List<Button> buttons;

    public static ToolBar create(final DrawingPane drawingPane) {
        final var buttons = Arrays.asList(
                createJavaFxButtonWith("Clear", ActionEvent.ACTION, ClearButtonHandler.create(drawingPane)),
                createJavaFxButtonWith("Rectangle", ActionEvent.ACTION, RectangleButtonHandler.create(drawingPane)),
                createJavaFxButtonWith("Circle", ActionEvent.ACTION, EllipseButtonHandler.create(drawingPane)),
                createJavaFxButtonWith("Triangle", ActionEvent.ACTION, TriangleButtonHandler.create(drawingPane)),
                createJavaFxButtonWith("Delete selection", ActionEvent.ACTION, DeleteButtonHandler.create(drawingPane))
        );

        return new ToolBar(buttons);
    }

    private static Button createJavaFxButtonWith(final String name,
                                                 final EventType<ActionEvent> event,
                                                 final EventHandler<? super ActionEvent> handler) {
        final var button = new Button(name);
        button.addEventFilter(event, handler);
        return button;
    }


}
