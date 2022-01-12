package drawing.commands.strategies;

import drawing.commands.Command;
import drawing.shapes.strategies.EdgeStrategy;
import drawing.shapes.strategies.infra.EdgeStrategyHandler;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class StrategyCommand implements Command {

    private final EdgeStrategy strategy;
    private final EdgeStrategyHandler strategyHandler;

    private EdgeStrategy last;

    public static Command create(final EdgeStrategy strategy,
                                 final EdgeStrategyHandler strategyHandler) {
        return new StrategyCommand(strategy, strategyHandler);
    }

    @Override
    public void execute() {
        last = strategyHandler.getStrategy();
        strategyHandler.update(strategy);
    }

    @Override
    public void undo() {
        strategyHandler.update(last);
    }

    @Override
    public Command duplicate() {
        return null;
    }

}
