package drawing.shapes.adapter;

import drawing.shapes.IShape;
import javafx.scene.layout.Pane;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class GroupShapeAdapter implements IShape {

    private final List<IShape> group;

    public static IShape create(final List<IShape> group) {
        return new GroupShapeAdapter(group);
    }

    public List<IShape> getGroup() {
        return group;
    }

    @Override
    public boolean isSelected() {
        return group.stream()
                .anyMatch(IShape::isSelected);
    }

    @Override
    public void setSelected(final boolean selected) {
        group.forEach(shape -> shape.setSelected(selected));
    }

    @Override
    public boolean isOn(final double x, final double y) {
        return group.stream()
                .anyMatch(shape -> shape.isOn(x, y));
    }

    @Override
    public void offset(final double x, final double y) {
        group.forEach(shape -> shape.offset(x, y));
    }

    @Override
    public void addTo(final Pane pane) {
        group.forEach(shape -> shape.addTo(pane));
    }

    @Override
    public void removeFrom(final Pane pane) {
        group.forEach(shape -> shape.removeFrom(pane));
    }
}
