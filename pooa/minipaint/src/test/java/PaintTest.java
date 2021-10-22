import drawing.PaintApplication;
import drawing.handler.bar.Observer;
import drawing.handler.bar.StatusBar;
import drawing.handler.dto.Triangle;
import drawing.shape.adapter.ShapeAdapter;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.junit.Assert;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.util.Iterator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
    public void should_update_status_bar() {

        should_draw_rectangle();
        assertTextInStatusBar("1 forme(s)");

        should_clear();
        assertTextInStatusBar("0 forme(s)");

    }

    private void assertTextInStatusBar(String s) {
        for (Iterator<Observer> it = app.getDrawingPane().getObservers(); it.hasNext(); ) {
            final Observer observer = it.next();
            if (observer instanceof StatusBar) {
                final StatusBar statusBar = (StatusBar) observer;
                Assert.assertEquals(s, statusBar.getText());
                return;
            }
        }

        Assert.fail("Status bar was not created");
    }

}