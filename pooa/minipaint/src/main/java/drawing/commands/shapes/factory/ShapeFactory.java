package drawing.commands.shapes.factory;

import drawing.handlers.dto.Point;
import drawing.handlers.dto.Triangle;
import drawing.shapes.IShape;
import drawing.shapes.adapter.ShapeAdapter;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ShapeFactory {

    public static ShapeFactory create() {
        return new ShapeFactory();
    }

    public IShape newShape(final ShapeType type, final ShapeParameters parameters) {
        switch (type) {
            case RECTANGLE:
                return newRectangle(parameters);
            case ELLIPSE:
                return newEllipse(parameters);
            case TRIANGLE:
                return newTriangle(parameters);
            default:
                throw new IllegalStateException("Illegal shape " + type);
        }
    }

    private IShape newTriangle(final ShapeParameters parameters) {
        final var a = Point.at(parameters.getOriginX(), parameters.getOriginY());
        final var b = Point.at(parameters.getDestinationX(), parameters.getDestinationY());
        final var triangle = Triangle.create(a, b, resolveLastPoint(a, b));

        triangle.getStyleClass()
                .add("triangle");
        return ShapeAdapter.create(triangle);
    }

    private Point resolveLastPoint(final Point a, final Point b) {
        return Point.at(a.getX() - b.getX() / 2, a.getY() - b.getY() / 2);
    }


    private IShape newEllipse(final ShapeParameters parameters) {
        final var definition = ShapeDefinition.from(parameters);
        final var ellipse = new Ellipse(
                definition.getX() + definition.getWidth() / 2,
                definition.getY() + definition.getHeight() / 2,
                definition.getWidth() / 2,
                definition.getHeight() / 2
        );
        ellipse.getStyleClass()
                .add("ellipse");
        return ShapeAdapter.create(ellipse);
    }

    private IShape newRectangle(final ShapeParameters parameters) {
        final var definition = ShapeDefinition.from(parameters);
        final var rectangle = new Rectangle(definition.getX(), definition.getX(), definition.getWidth(), definition.getWidth());

        rectangle.getStyleClass()
                .add("rectangle");

        return ShapeAdapter.create(rectangle);
    }

    @Getter
    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    private static class ShapeDefinition {
        private final double x;
        private final double y;
        private final double width;
        private final double height;

        public static ShapeDefinition from(final ShapeParameters parameters) {
            final var x = Math.min(parameters.getOriginX(), parameters.getDestinationX());
            final var y = Math.min(parameters.getOriginY(), parameters.getDestinationY());
            final var width = Math.abs(parameters.getDestinationX() - parameters.getOriginX());
            final var height = Math.abs(parameters.getDestinationY() - parameters.getOriginY());
            return new ShapeDefinition(x, y, width, height);
        }
    }
}
