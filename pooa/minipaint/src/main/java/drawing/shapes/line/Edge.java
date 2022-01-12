package drawing.shapes.line;

import drawing.shapes.strategies.EdgeStrategy;
import drawing.shapes.IShape;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Path;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Edge implements IShape {

    private final IShape from;
    private final IShape to;
    private final Path shape;
    @Getter
    private EdgeStrategy edgeStrategy;

    private boolean selected;

    public static IShape from(final IShape from,
                              final IShape to,
                              final EdgeStrategy edgeStrategy) {

        return new Edge(from, to, edgeStrategy.buildPath(from, to))
                .setEdgeStrategy(edgeStrategy);
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
        return from(from.duplicate(), to.duplicate(), edgeStrategy);
    }

    @Override
    public ObservableValue<Number> translateCenterX() {
        return null;
    }

    @Override
    public ObservableValue<Number> translateCenterY() {
        return null;
    }

    public Edge setEdgeStrategy(final EdgeStrategy edgeStrategy) {
        this.edgeStrategy = edgeStrategy;
        return this;
    }
}
