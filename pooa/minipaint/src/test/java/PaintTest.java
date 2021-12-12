import drawing.PaintApplication;
import drawing.bar.status.StatusBar;
import drawing.bar.status.observers.Observer;
import drawing.handlers.dto.Triangle;
import drawing.shapes.adapter.GroupShapeAdapter;
import drawing.shapes.adapter.ShapeAdapter;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.junit.Assert;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

public class PaintTest extends ApplicationTest {

    PaintApplication app;

    @Override
    public void start(Stage stage) {
        try {
            app = new PaintApplication();
            app.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void should_draw_circle_programmatically() {
        interact(() -> {
            app.getDrawingPane().addShape(ShapeAdapter.create(new Ellipse(20, 20, 30, 30)));
        });
        Iterator it = app.getDrawingPane().getChildren().iterator();
        assertTrue(it.next() instanceof Ellipse);
        assertFalse(it.hasNext());
    }

    @Test
    public void should_draw_circle() {
        // given:
        draw_circle();
        //press(MouseButton.PRIMARY); moveBy(30,30); release(MouseButton.PRIMARY);

        // then:
        var it = app.getDrawingPane().getChildren().iterator();
        assertTrue(it.next() instanceof Ellipse);
        assertFalse(it.hasNext());
    }

    private void draw_circle() {
        clickOn("Circle");
        moveBy(60, 60);

        // when:
        drag().dropBy(30, 30);
    }

    @Test
    public void should_draw_rectangle() {
        // given:
        draw_rectangle();

        // then:
        var it = app.getDrawingPane().getChildren().iterator();
        assertTrue(it.next() instanceof Rectangle);
        assertFalse(it.hasNext());
    }

    private void draw_rectangle() {
        clickOn("Rectangle");
        moveBy(0, 60);

        // when:
        drag().dropBy(70, 40);
    }

    @Test
    public void should_draw_triangle() {
        // given:
        clickOn("Triangle");
        moveBy(0, 60);

        // when:
        drag().dropBy(70, 40);

        // then:
        var it = app.getDrawingPane().getChildren().iterator();
        assertTrue(it.next() instanceof Triangle);
        assertFalse(it.hasNext());
    }

    @Test
    public void should_clear() {
        // given:
        clickOn("Rectangle");
        moveBy(30, 60).drag().dropBy(70, 40);
        clickOn("Circle");
        moveBy(-30, 160).drag().dropBy(70, 40);

        // when:
        clickOn("Clear");

        // then:
        assertFalse(app.getDrawingPane().getChildren().iterator().hasNext());
    }

    @Test
    public void should_select_rectange() {
        should_draw_rectangle();
        clickOn(".rectangle");

        final var selectedShapes = app.getDrawingPane().getSelectedShapes();
        assertEquals(1, selectedShapes.size());
    }

    @Test
    public void should_update_status_bar() {
        should_select_rectange();
        assertTextInStatusBar(Arrays.asList("1 forme(s)", "1 selected forme(s)"));

        should_clear();
        assertTextInStatusBar(Arrays.asList("0 forme(s)", "0 selected forme(s)"));

    }

    @Test
    public void should_delete_selected_and_empty_status_bar() {
        should_select_rectange();
        clickOn("Delete selection");

        assertTextInStatusBar(Arrays.asList("0 forme(s)", "0 selected forme(s)"));
    }

    @Test
    public void should_group_shapes() {
        draw_rectangle();
        draw_circle();

        clickOn(".rectangle");

        press(KeyCode.SHIFT)
                .clickOn(".ellipse")
                .release(KeyCode.SHIFT);

        clickOn("Group");

        final var shapes = app.getDrawingPane().getShapes();
        Assert.assertEquals(GroupShapeAdapter.class, shapes.get(0).getClass());

    }

    @Test
    public void should_undo_one_shape() {
        draw_circle();
        clickOn("Clear");
        draw_rectangle();
        clickOn("Clear");
        clickOn("Undo");

        var shapes = app.getDrawingPane().getShapes();

        Assert.assertEquals(1, shapes.size());

        clickOn("Undo");

        clickOn("Undo");

        shapes = app.getDrawingPane().getShapes();

        Assert.assertEquals(1, shapes.size());

    }

    private void assertTextInStatusBar(final List<String> expectedTextInStatusBar) {
        for (Iterator<Observer> it = app.getDrawingPane().getObservers(); it.hasNext(); ) {
            final Observer observer = it.next();
            if (observer instanceof StatusBar) {
                final StatusBar statusBar = (StatusBar) observer;
                Assert.assertEquals(expectedTextInStatusBar, statusBar.getText());
                return;
            }
        }

        Assert.fail("Status bar was not created");
    }

}