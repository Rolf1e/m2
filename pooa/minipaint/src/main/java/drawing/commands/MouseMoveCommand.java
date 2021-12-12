package drawing.commands;

import drawing.handlers.dto.Coordinate;
import drawing.shapes.IShape;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class MouseMoveCommand implements Command {

    private final IShape shape;

    private final Coordinate offset;
    private final Coordinate oldOffset;

    public static Command create(final IShape shape,
                                 final Coordinate offset,
                                 final Coordinate oldOffset) {

        return new MouseMoveCommand(shape, offset, oldOffset);

    }

    @Override
    public void execute() {
        updateOffSet(shape, offset);
    }

    @Override
    public void undo() {
        updateOffSet(shape, oldOffset);
    }

    @Override
    public Command duplicate() {
        return create(shape, offset, oldOffset);
    }

    public static void updateOffSet(final IShape shape,
                                    final Coordinate offset) {
        shape.offset(offset.getX(), offset.getY());
    }
}
