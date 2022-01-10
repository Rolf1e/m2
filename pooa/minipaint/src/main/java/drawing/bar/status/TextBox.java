package drawing.bar.status;

import javafx.scene.control.Label;
import lombok.Getter;

public class TextBox {

    @Getter
    private final Label label;
    private final String baseText;

    public static TextBox createWithBaseText(final String baseText) {
        return new TextBox(baseText);
    }

    private TextBox(final String baseText) {
        this.baseText = baseText;
        this.label = new Label(baseText);
    }

    public void update(final String text) {
        label.setText(text + baseText);
    }

    public String getText() {
        return label.getText();
    }
}
