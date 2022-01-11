package drawing.bar.status;

import drawing.bar.status.observers.Observer;
import drawing.bar.status.observers.ObserverParameters;
import javafx.scene.layout.HBox;

public class ErrorBar extends HBox implements Observer {

    private static final String ERROR_TEXT = "Last error: ";

    private final TextBox text;

    public static ErrorBar createEmpty() {
        return new ErrorBar(TextBox.createWithBaseText(ERROR_TEXT));
    }

    private ErrorBar(final TextBox text) {
        this.text = text;
        getChildren().add(text.getLabel());
    }

    @Override
    public void update(final ObserverParameters parameters) {
        text.update(parameters.getError());
    }
}
