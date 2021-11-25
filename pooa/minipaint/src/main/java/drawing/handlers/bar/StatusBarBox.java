package drawing.handlers.bar;

import javafx.scene.control.Label;
import lombok.Getter;

class StatusBarBox {

    @Getter
    private final Label label;
    private final String baseText;

    public static StatusBarBox createWithBaseText(final String baseText) {
        return new StatusBarBox(baseText);
    }

    private StatusBarBox(final String baseText) {
        this.baseText = baseText;
        this.label = new Label(baseText);
    }

    public void update(final int size) {
        label.setText(size + baseText);
    }

    public String getText() {
        return label.getText();
    }
}
