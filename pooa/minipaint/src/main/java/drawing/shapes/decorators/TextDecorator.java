package drawing.shapes.decorators;

import drawing.shapes.IShape;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class TextDecorator implements IShape {

    private final IShape wrappee;
    private final Text textBox;

    public static IShape from(final IShape wrappee,
                              final String text) {

        final var label = new Text(text);
        label.translateXProperty().bind(wrappee.translateCenterX());
        label.translateYProperty().bind(wrappee.translateCenterY());
        return new TextDecorator(wrappee, label);
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
    }

    @Override
    public void addTo(final Pane pane) {
        wrappee.addTo(pane);
        pane.getChildren()
                .add(textBox);
    }

    @Override
    public void removeFrom(final Pane pane) {
        wrappee.removeFrom(pane);
        pane.getChildren()
                .remove(textBox);
    }

    @Override
    public IShape duplicate() {
        return wrappee.duplicate();
    }

    @Override
    public ObservableValue<Number> translateCenterX() {
        return null;
    }

    @Override
    public ObservableValue<Number> translateCenterY() {
        return null;
    }
}
