package drawing.handlers.buttons;

import drawing.commands.Command;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ButtonHandler implements EventHandler<ActionEvent> {

    private final Command command;

    public static ButtonHandler create(final Command command) {
        return new ButtonHandler(command);
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        command.execute();
    }
}
