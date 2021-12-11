package drawing.handlers.buttons;

import drawing.commands.Command;
import drawing.commands.history.CommandHistory;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ButtonHandler implements EventHandler<ActionEvent> {

    private final Command command;
    private final CommandHistory history;

    public static ButtonHandler create(final Command command,
                                       final CommandHistory history) {
        return new ButtonHandler(command, history);
    }

    @Override
    public void handle(final ActionEvent actionEvent) {
        history.execute(command);
    }
}
