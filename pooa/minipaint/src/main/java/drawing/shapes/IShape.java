package drawing.shapes;

import javafx.beans.value.ObservableValue;
import javafx.scene.layout.Pane;

public interface IShape {
    boolean isSelected();

    void setSelected(boolean selected);

    boolean isOn(double x, double y);

    void offset(double x, double y);

    void addTo(Pane pane);

    void removeFrom(Pane pane);

    IShape duplicate();

    ObservableValue<Number> translateCenterX();

    ObservableValue<Number> translateCenterY();
}
