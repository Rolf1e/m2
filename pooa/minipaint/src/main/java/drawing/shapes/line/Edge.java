package drawing.shapes.line;

import drawing.shapes.IShape;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Edge implements IShape {

    private final IShape from;
    private final IShape to;
    private final Line line;

    private boolean selected;

    public static IShape from(final IShape from,
                              final IShape to) {

        final var line = new Line();
        line.startXProperty().bind(from.translateCenterX());
        line.startYProperty().bind(from.translateCenterY());
        line.endXProperty().bind(to.translateCenterX());
        line.endYProperty().bind(to.translateCenterY());
        return new Edge(from, to, line);
    }

    @Override
    public boolean isSelected() {
        return selected;
    }

    @Override
    public void setSelected(final boolean selected) {
        this.selected = selected;
        from.setSelected(selected);
        to.setSelected(selected);
        if (selected) {
            line.setStyle("-fx-stroke-width: 5;");
        } else {
            line.setStyle("-fx-stroke-width: 3;");
        }
    }

    @Override
    public boolean isOn(final double x,
                        final double y) {
        return from.isOn(x, y) || to.isOn(x, y) || onLine(x, y);
    }

    private boolean onLine(final double x,
                           final double y) {
        return line.getBoundsInParent()
                .contains(x, y);
    }

    @Override
    public void offset(final double x,
                       final double y) {
        // Will move with shapes
    }

    @Override
    public void addTo(final Pane pane) {
        pane.getChildren()
                .add(line);
    }

    @Override
    public void removeFrom(final Pane pane) {
        pane.getChildren()
                .remove(line);
    }

    @Override
    public IShape duplicate() {
        return from(from.duplicate(), to.duplicate());
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
