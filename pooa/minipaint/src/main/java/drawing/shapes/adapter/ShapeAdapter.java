package drawing.shapes.adapter;

import drawing.shapes.IShape;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

public class ShapeAdapter implements IShape {

    private final Shape shape;
    private boolean selected;

    public static IShape create(final Shape shape) {
        return new ShapeAdapter(shape);
    }

    private ShapeAdapter(final Shape shape) {
        this.shape = shape;
        this.selected = false;
    }

    @Override
    public boolean isSelected() {
        return selected;
    }

    @Override
    public void setSelected(final boolean selected) {
        this.selected = selected;
        if (selected) {
            shape.setStyle("-fx-stroke-width: 10;");
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

        shape.setTranslateX(shape.getTranslateX() + x);
        shape.setTranslateY(shape.getTranslateY() + y);
    }

    @Override
    public void addTo(final Pane pane) {
        pane.getChildren().add(shape);
    }

    @Override
    public void removeFrom(final Pane pane) {
        pane.getChildren().remove(shape);
    }

    @Override
    public String toString() {
        return "ShapeAdapter{" +
                "shape=" + shape +
                ", selected=" + selected +
                '}';
    }
}
