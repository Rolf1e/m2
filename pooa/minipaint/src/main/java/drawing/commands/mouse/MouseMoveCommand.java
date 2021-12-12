package drawing.commands.mouse;

import drawing.commands.Command;
import drawing.shapes.IShape;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class MouseMoveCommand implements Command {

    private final IShape shape;
    private final double oldOffsetX;
    private final double oldOffsetY;

    public static Command create(final IShape shape,
                                 final double oldOffsetX,
                                 final double oldOffsetY) {

        return new MouseMoveCommand(shape, oldOffsetX, oldOffsetY);
    }

    @Override
    public void execute() {
        // make no sense
    }

    @Override
    public void undo() {
        shape.offset(oldOffsetX, oldOffsetY);
    }

    @Override
    public Command duplicate() {
        return create(shape, oldOffsetX, oldOffsetY);
    }
}
