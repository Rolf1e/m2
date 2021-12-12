package drawing.commands.history;

import drawing.commands.Command;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.Stack;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class CommandHistory {

    private final Stack<Command> commands;
    private Command lastCommand;

    public static CommandHistory newCommandHistory() {
        return new CommandHistory(new Stack<>());
    }

    public void execute(final Command command) {
        command.execute();
        commands.add(command.duplicate()); // We duplicate instance as some commands are shared at creation
    }

    public void undo() {
        if (commands.isEmpty()) {
            return; // nothing to do if no commands done
        }

        final var command = commands.lastElement();
        command.undo();
        lastCommand = command;
        commands.remove(command);
    }

    public void redo() {
        Optional.ofNullable(lastCommand)
                .ifPresent(this::execute);
    }


}
