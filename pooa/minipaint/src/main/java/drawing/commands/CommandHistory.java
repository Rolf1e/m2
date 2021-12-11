package drawing.commands;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.Stack;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class CommandHistory {

    private final Stack<Command> commands;

    public static CommandHistory newCommandHistory() {
        return new CommandHistory(new Stack<>());
    }

    public void execute(final Command command) {
        command.execute();
        commands.add(command);
    }

    public void undo() {
        final var command = commands.lastElement();
        command.undo();
        commands.remove(command);
    }


}
