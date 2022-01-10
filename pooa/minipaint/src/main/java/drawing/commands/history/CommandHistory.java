package drawing.commands.history;

import drawing.bar.status.observers.ObserverParameters;
import drawing.commands.Command;
import drawing.panes.DrawingPane;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.Stack;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class CommandHistory {

    private final Stack<Command> commands;
    private final DrawingPane pane;
    private Command lastCommand;

    public static CommandHistory newCommandHistory(final DrawingPane pane) {
        return new CommandHistory(new Stack<>(), pane);
    }

    public void execute(final Command command) {
        try {
            command.execute();
            commands.add(command.duplicate()); // We duplicate instance as some commands are shared at creation
        } catch (final Exception e) {
            ObserverParameters.getInstance()
                    .setError(e.getMessage());
            pane.updateObservers();
        }
    }

    public void undo() {
        try {
            if (commands.isEmpty()) {
                return; // nothing to do if no commands done
            }

            final var command = commands.lastElement();
            command.undo();
            lastCommand = command;
            commands.remove(command);
        } catch (final Exception e) {
            ObserverParameters.getInstance()
                    .setError(e.getMessage());
            pane.updateObservers();
        }
    }

    public void redo() {
        Optional.ofNullable(lastCommand)
                .ifPresent(this::execute);
    }

}
