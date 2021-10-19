package drawing.handler.bar;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;


public class StatusBar extends HBox implements Observer {

    private static final String FORME_S = " forme(s)";
    private final Label label;

    public static StatusBar createEmpty() {
        return new StatusBar();
    }

    private StatusBar() {
        this.label = new Label(0 + FORME_S);
        getChildren().add(label);
    }

    @Override
    public void update(final int size) {
        label.setText(size + FORME_S);
    }

    public String getText() {
        return label.getText();
    }
}

