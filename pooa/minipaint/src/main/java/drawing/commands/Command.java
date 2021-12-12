package drawing.commands;

import drawing.commands.history.CommandHistory;

public interface Command {

    void execute();

    void undo();

    /**
     * To ease duplication in {@link CommandHistory}
     */
    Command duplicate();
}
