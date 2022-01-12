package drawing.shapes.strategies.infra;

import drawing.shapes.strategies.EdgeStrategy;
import drawing.shapes.strategies.LineStrategy;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EdgeStrategyHandler {

    private static EdgeStrategyHandler INSTANCE;

    @Getter
    private EdgeStrategy strategy;

    public static EdgeStrategyHandler getInstance() {
        if (Objects.isNull(INSTANCE)) {
            INSTANCE = new EdgeStrategyHandler(LineStrategy.create());
        }
        return INSTANCE;
    }

    public synchronized void update(final EdgeStrategy strategy) {
        this.strategy = strategy;
    }

}
