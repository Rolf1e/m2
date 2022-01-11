package drawing.shapes.line;

import drawing.shapes.IShape;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.Pane;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Edge implements IShape {

    private final IShape from;
    private final IShape to;
    private final Path shape;

    private boolean selected;

    public static IShape from(final IShape from,
                              final IShape to) {

        final var shape = new Path();

        final var moveTo = new MoveTo();

        final var lineToStart = new LineTo();

        moveTo.xProperty().bind(from.translateCenterX());
        moveTo.yProperty().bind(from.translateCenterY());
        lineToStart.xProperty().bind(to.translateCenterX());
        lineToStart.yProperty().bind(to.translateCenterY());

        shape.getElements().add(moveTo);
        shape.getElements().add(lineToStart);

        return new Edge(from, to, shape);
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
            shape.setStyle("-fx-stroke-width: 5;");
        } else {
            shape.setStyle("-fx-stroke-width: 3;");
        }
    }

    @Override
    public boolean isOn(final double x,
                        final double y) {
        return from.isOn(x, y) || to.isOn(x, y) || onLine(x, y);
    }

    private boolean onLine(final double x,
                           final double y) {
        return shape.getBoundsInParent()
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
                .add(shape);
    }

    @Override
    public void removeFrom(final Pane pane) {
        pane.getChildren()
                .remove(shape);
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
