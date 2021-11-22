import drawing.PaintApplication;
import drawing.handler.bar.Observer;
import drawing.handler.bar.StatusBarShapesObserver;
import drawing.handler.dto.Triangle;
import drawing.shape.adapter.ShapeAdapter;
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
        clickOn("Circle");
        moveBy(60, 60);

        // when:
        drag().dropBy(30, 30);
        //press(MouseButton.PRIMARY); moveBy(30,30); release(MouseButton.PRIMARY);

        // then:
        var it = app.getDrawingPane().getChildren().iterator();
        assertTrue(it.next() instanceof Ellipse);
        assertFalse(it.hasNext());
    }

    @Test
    public void should_draw_rectangle() {
        // given:
        clickOn("Rectangle");
        moveBy(0, 60);

        // when:
        drag().dropBy(70, 40);

        // then:
        var it = app.getDrawingPane().getChildren().iterator();
        assertTrue(it.next() instanceof Rectangle);
        assertFalse(it.hasNext());
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

    private void assertTextInStatusBar(final List<String> expectedTextInStatusBar) {
        for (Iterator<Observer> it = app.getDrawingPane().getObservers(); it.hasNext(); ) {
            final Observer observer = it.next();
            if (observer instanceof StatusBarShapesObserver) {
                final StatusBarShapesObserver statusBarShapesObserver = (StatusBarShapesObserver) observer;
                Assert.assertEquals(expectedTextInStatusBar, statusBarShapesObserver.getText());
                return;
            }
        }

        Assert.fail("Status bar was not created");
    }

}