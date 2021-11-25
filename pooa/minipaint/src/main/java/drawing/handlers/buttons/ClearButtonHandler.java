package drawing.handlers.buttons;

import drawing.panes.DrawingPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ClearButtonHandler implements EventHandler<ActionEvent> {

    private final DrawingPane drawingPane;

    public static EventHandler<ActionEvent> create(final DrawingPane drawingPane) {
        return new ClearButtonHandler(drawingPane);
    }

    @Override
    public void handle(ActionEvent event) {
        this.drawingPane.clear();
    }
}
