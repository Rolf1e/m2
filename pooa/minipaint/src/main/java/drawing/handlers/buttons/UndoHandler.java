package drawing.handlers.buttons;

import drawing.commands.CommandHistory;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class UndoHandler implements EventHandler<ActionEvent> {

    private final CommandHistory history;

    public static EventHandler<? super ActionEvent> create(final CommandHistory history) {
        return new UndoHandler(history);
    }

    @Override
    public void handle(final ActionEvent actionEvent) {
        history.undo();
    }
}
