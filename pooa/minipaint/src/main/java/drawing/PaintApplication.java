package drawing;

import drawing.handler.bar.StatusBar;
import drawing.handler.button.ClearButtonHandler;
import drawing.handler.button.shapes.EllipseButtonHandler;
import drawing.handler.button.shapes.RectangleButtonHandler;
import drawing.handler.button.shapes.TriangleButtonHandler;
import drawing.pane.DrawingPane;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Created by lewandowski on 20/12/2020.
 */
public class PaintApplication extends Application {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    private Scene scene;
    private BorderPane root;
    private DrawingPane drawingPane;

    private Button clearButton;
    private Button rectangleButton;
    private Button circleButton;
    private Button triangleButton;

    @Override
    public void start(Stage primaryStage) throws Exception {
        root = new BorderPane();
        scene = new Scene(root, WIDTH, HEIGHT);

        root.getStylesheets().add(
                PaintApplication.class.getClassLoader().getResource("style/Paint.css").toExternalForm());

        drawingPane = new DrawingPane();
        drawingPane.getStyleClass().add("drawingPane");
        root.setCenter(drawingPane);

        HBox hBox = new HBox();
        clearButton = new Button("Clear");
        clearButton.addEventFilter(ActionEvent.ACTION, new ClearButtonHandler(drawingPane));

        rectangleButton = new Button("Rectangle");
        rectangleButton.addEventFilter(ActionEvent.ACTION, new RectangleButtonHandler(drawingPane));

        circleButton = new Button("Circle");
        circleButton.addEventFilter(ActionEvent.ACTION, new EllipseButtonHandler(drawingPane));

        triangleButton = new Button("Triangle");
        triangleButton.addEventFilter(ActionEvent.ACTION, TriangleButtonHandler.create(drawingPane));

        hBox.getChildren().addAll(clearButton, rectangleButton, circleButton, triangleButton);
        hBox.setPadding(new Insets(5));
        hBox.setSpacing(5.0);
        hBox.getStyleClass().add("toolbar");
        root.setTop(hBox);


        createStatusBar();


        primaryStage.setTitle("Drawing");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    private Button createButton(final String label, final EventHandler<Event> action) {
        final Button button = new Button(label);
        button.addEventFilter(ActionEvent.ACTION, action);
        return button;
    }


    private void createStatusBar() {
        final StatusBar statusBar = StatusBar.createEmpty();
        statusBar.getStyleClass().add("status_bar");
        root.setBottom(statusBar);
        drawingPane.addObserver(statusBar);
    }


    public DrawingPane getDrawingPane() {
        return drawingPane;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
