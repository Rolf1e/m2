package drawing.handler.bar;

import javafx.scene.shape.Shape;

import java.util.List;

public interface Observer {

    void update(final List<Shape> shapes);

}
