package drawing.commands.history;

import drawing.commands.Command;
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
        commands.add(command.duplicate()); // We duplicate instance as some commands are shared at creation
        printStack();
    }

    private void printStack() {
        System.out.println("History: " + commands);
    }

    public void undo() {
        final var command = commands.lastElement();
        command.undo();
        commands.remove(commands.size() - 1);
        printStack();
    }


}
