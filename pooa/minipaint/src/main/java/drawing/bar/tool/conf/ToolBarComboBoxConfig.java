package drawing.bar.tool.conf;

import drawing.commands.strategies.StrategyCommand;
import drawing.panes.DrawingPane;
import drawing.shapes.strategies.ArcStrategy;
import drawing.shapes.strategies.EdgeStrategy;
import drawing.shapes.strategies.LineStrategy;
import drawing.shapes.strategies.infra.EdgeStrategyHandler;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum ToolBarComboBoxConfig {
    LINE_TYPES(Arrays.asList("Normal", "Orthogonal"));

    private final ComboBox<String> comboxBox;
    private final List<String> titles;

    ToolBarComboBoxConfig(final List<String> titles) {
        this.titles = titles;
        this.comboxBox = transform(titles);
    }

    public static <T> void addEventListener(final ComboBox<T> comboBox,
                                            final ChangeListener<T> function) {

        comboBox.getSelectionModel()
                .selectedItemProperty()
                .addListener(function);
    }

    public <T> ChangeListener<T> getListener(final DrawingPane pane) {
        final var history = pane.getHistory();
        final var strategyHandler = EdgeStrategyHandler.getInstance();
        switch (this) {
            case LINE_TYPES:
                return (options, oldValue, newValue) -> {
                    final EdgeStrategy strategy;
                    if ("Orthogonal".equals(newValue)) {
                        strategy = ArcStrategy.create();
                    } else {
                        strategy = LineStrategy.create();
                    }
                    // Default
                    history.execute(StrategyCommand.create(strategy, strategyHandler));
                };
            default:
                throw new IllegalStateException("Invalid config " + this);
        }
    }

    private static ComboBox<String> transform(final List<String> fields) {
        return new ComboBox<>(FXCollections.observableList(fields));
    }
}
