package drawing.bar.status;

import drawing.bar.status.observers.Observer;
import drawing.bar.status.observers.ObserverParameters;
import javafx.scene.layout.HBox;

import java.util.Arrays;
import java.util.List;

public class StatusBar extends HBox implements Observer {

    private static final String BASE_TEXT_SELECTED = " selected forme(s)";
    private static final String BASE_TEXT = " forme(s)";

    private final TextBox shapes;
    private final TextBox selectedShapes;

    public static StatusBar createEmpty() {
        return new StatusBar();
    }

    private StatusBar() {
        this.shapes = TextBox.createWithBaseText(BASE_TEXT);
        this.selectedShapes = TextBox.createWithBaseText(BASE_TEXT_SELECTED);
        getChildren().addAll(shapes.getLabel(), selectedShapes.getLabel());
    }

    @Override
    public void update(final ObserverParameters parameters) {
        shapes.update(String.valueOf(parameters.getShapesSize()));
        selectedShapes.update(String.valueOf(parameters.getSelectedShapesSize()));
    }

    public List<String> getText() {
        return Arrays.asList(
                shapes.getText(),
                selectedShapes.getText()
        );
    }
}

