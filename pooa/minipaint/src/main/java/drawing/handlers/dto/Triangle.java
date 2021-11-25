package drawing.handlers.dto;

import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Stream;

public class Triangle extends Polygon {

    public static Shape create(final Point a,
                               final Point b,
                               final Point c) {

        final var points = Stream.of(a, b, c)
                .map(point -> Arrays.asList(point.getX(), point.getY()))
                .flatMap(Collection::stream)
                .mapToDouble(d -> d)
                .toArray();

        return new Triangle(points);
    }

    private Triangle(final double... points) {
        super(points);
    }

}
