package drawing.handler.button;

import drawing.pane.DrawingPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class DeleteButtonHandler implements EventHandler<ActionEvent> {

    private final DrawingPane drawingPane;

    public static EventHandler<ActionEvent> create(final DrawingPane drawingPane) {
        return new DeleteButtonHandler(drawingPane);
    }

    @Override
    public void handle(final ActionEvent actionEvent) {
        drawingPane.getSelectedShapes()
                .forEach(drawingPane::removeShape);
    }
}
