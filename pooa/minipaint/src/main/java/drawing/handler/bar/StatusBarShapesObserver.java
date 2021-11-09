package drawing.handler.bar;

import javafx.scene.layout.HBox;

import java.util.Arrays;
import java.util.List;

public class StatusBarShapesObserver extends HBox implements Observer {

    private static final String BASE_TEXT_SELECTED = " selected forme(s)";
    private static final String BASE_TEXT = " forme(s)";
    private final StatusBarBox shapes;
    private final StatusBarBox selectedShapes;

    public static StatusBarShapesObserver createEmpty() {
        return new StatusBarShapesObserver();
    }

    private StatusBarShapesObserver() {
        this.shapes = StatusBarBox.createWithBaseText(BASE_TEXT);
        this.selectedShapes = StatusBarBox.createWithBaseText(BASE_TEXT_SELECTED);
        getChildren().addAll(shapes.getLabel(), selectedShapes.getLabel());
    }

    @Override
    public void update(final ObserverParameters parameters) {
        shapes.update(parameters.getShapesSize());
        selectedShapes.update(parameters.getSelectedShapesSize());
    }

    public List<String> getText() {
        return Arrays.asList(
                shapes.getText(),
                selectedShapes.getText()
        );
    }
}

