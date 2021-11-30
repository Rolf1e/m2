package drawing.commands;

public interface Command {

    void execute();

    void undo();
}
