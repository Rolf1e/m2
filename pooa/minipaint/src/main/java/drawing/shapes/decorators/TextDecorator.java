package drawing.shapes.decorators;

import drawing.bar.status.TextBox;
import drawing.shapes.IShape;
import javafx.scene.layout.Pane;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class TextDecorator implements IShape {

    private final IShape wrappee;
    private final TextBox textBox;

    public static IShape create(final IShape wrappee,
                                final String text) {
        return new TextDecorator(wrappee, TextBox.createWithBaseText(text));
    }

    @Override
    public boolean isSelected() {
        return wrappee.isSelected();
    }

    @Override
    public void setSelected(final boolean selected) {
        wrappee.setSelected(selected);
    }

    @Override
    public boolean isOn(final double x,
                        final double y) {
        return wrappee.isOn(x, y);
    }

    @Override
    public void offset(final double x,
                       final double y) {

        wrappee.offset(x, y);
        final var label = textBox.getLabel();
        label.setTranslateX(label.getTranslateX() + x);
        label.setTranslateY(label.getTranslateY() + y);
    }

    @Override
    public void addTo(final Pane pane) {
        wrappee.addTo(pane);
        pane.getChildren()
                .add(textBox.getLabel());
    }

    @Override
    public void removeFrom(final Pane pane) {
        wrappee.removeFrom(pane);
        pane.getChildren()
                .remove(textBox.getLabel());
    }

    @Override
    public IShape duplicate() {
        return wrappee.duplicate();
    }
}
