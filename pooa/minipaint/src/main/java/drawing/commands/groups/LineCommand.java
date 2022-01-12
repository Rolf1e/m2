package drawing.commands.groups;

import drawing.commands.Command;
import drawing.panes.DrawingPane;
import drawing.shapes.strategies.infra.EdgeStrategyHandler;
import drawing.shapes.IShape;
import drawing.shapes.line.Edge;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class LineCommand implements Command {

    private final DrawingPane pane;
    private final EdgeStrategyHandler strategyHandler;

    private IShape from;
    private IShape to;
    private IShape edge;

    public static Command create(final DrawingPane pane,
                                 final EdgeStrategyHandler strategyHandler) {
        return new LineCommand(pane, strategyHandler);
    }

    private LineCommand(final DrawingPane pane,
                        final IShape from,
                        final IShape to,
                        final IShape edge,
                        final EdgeStrategyHandler strategyHandler) {
        this.pane = pane;
        this.from = from;
        this.to = to;
        this.edge = edge;
        this.strategyHandler = strategyHandler;
    }

    @Override
    public void execute() {
        final var selected = pane.getSelectedShapes();
        if (2 == selected.size()) {
            from = selected.get(0);
            to = selected.get(1);
            edge = Edge.from(from, to, strategyHandler.getStrategy());
            pane.addShape(edge);
        }

        throw new IllegalStateException("Select only two shapes !");
    }

    @Override
    public void undo() {
        pane.removeShape(edge);
    }

    @Override
    public Command duplicate() {
        return new LineCommand(pane, from, to, edge, strategyHandler);
    }
}
