package drawing.handlers.buttons;

import drawing.commands.history.CommandHistory;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class RedoHandler implements EventHandler<ActionEvent> {

    private final CommandHistory history;

    public static EventHandler<ActionEvent> create(final CommandHistory history) {
        return new RedoHandler(history);
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        history.redo();
    }
}
