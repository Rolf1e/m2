package drawing.bar.status.observers;

import lombok.Getter;

import java.util.Objects;

@Getter
public class ObserverParametersHandler {

    private static ObserverParametersHandler INSTANCE;

    private int shapesSize;
    private int selectedShapesSize;
    private String error;


    public static ObserverParametersHandler getInstance() {
        if (Objects.isNull(INSTANCE)) {
            INSTANCE = new ObserverParametersHandler();
        }
        return INSTANCE;
    }

    private ObserverParametersHandler() {
        this.shapesSize = 0;
        this.selectedShapesSize = 0;
        this.error = "";
    }

    public synchronized ObserverParametersHandler setShapesSize(int shapesSize) {
        this.shapesSize = shapesSize;
        return this;
    }

    public synchronized ObserverParametersHandler setSelectedShapesSize(int selectedShapesSize) {
        this.selectedShapesSize = selectedShapesSize;
        return this;
    }

    public synchronized ObserverParametersHandler setError(String error) {
        this.error = error;
        return this;
    }
}
