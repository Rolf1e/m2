package drawing.bar.status.observers;

import lombok.Getter;

import java.util.Objects;

@Getter
public class ObserverParameters {

    private static ObserverParameters INSTANCE;

    private int shapesSize;
    private int selectedShapesSize;
    private String error;


    public static ObserverParameters getInstance() {
        if (Objects.isNull(INSTANCE)) {
            INSTANCE = new ObserverParameters();
        }
        return INSTANCE;
    }

    private ObserverParameters() {
        this.shapesSize = 0;
        this.selectedShapesSize = 0;
        this.error = "";
    }

    public synchronized ObserverParameters setShapesSize(int shapesSize) {
        this.shapesSize = shapesSize;
        return this;
    }

    public synchronized ObserverParameters setSelectedShapesSize(int selectedShapesSize) {
        this.selectedShapesSize = selectedShapesSize;
        return this;
    }

    public synchronized ObserverParameters setError(String error) {
        this.error = error;
        return this;
    }
}
