package drawing.handlers.bar.observers;

import lombok.Getter;

@Getter
public class ObserverParameters {

    private final int shapesSize;
    private final int selectedShapesSize;

    public static ObserverParameters create(final int shapes,
                                            final int selectedShapes) {
        return new ObserverParameters(shapes, selectedShapes);
    }

    private ObserverParameters(final int shapesSize,
                               final int selectedShapesSize) {
        this.shapesSize = shapesSize;
        this.selectedShapesSize = selectedShapesSize;
    }
}
