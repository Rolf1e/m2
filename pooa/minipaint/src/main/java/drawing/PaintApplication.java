package drawing;

import drawing.handlers.bar.StatusBarShapesObserver;
import drawing.handlers.buttons.ClearButtonHandler;
import drawing.handlers.buttons.DeleteButtonHandler;
import drawing.handlers.buttons.shapes.EllipseButtonHandler;
import drawing.handlers.buttons.shapes.RectangleButtonHandler;
import drawing.handlers.buttons.shapes.TriangleButtonHandler;
import drawing.panes.DrawingPane;
import javafx.application.Application;
import javafx.event.ActionEvent;
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
    private Button deleteButton;

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
        clearButton.addEventFilter(ActionEvent.ACTION, ClearButtonHandler.create(drawingPane));

        rectangleButton = new Button("Rectangle");
        rectangleButton.addEventFilter(ActionEvent.ACTION, new RectangleButtonHandler(drawingPane));

        circleButton = new Button("Circle");
        circleButton.addEventFilter(ActionEvent.ACTION, new EllipseButtonHandler(drawingPane));

        triangleButton = new Button("Triangle");
        triangleButton.addEventFilter(ActionEvent.ACTION, TriangleButtonHandler.create(drawingPane));

        deleteButton = new Button("Delete selection");
        deleteButton.addEventFilter(ActionEvent.ACTION, DeleteButtonHandler.create(drawingPane));

        hBox.getChildren().addAll(clearButton, rectangleButton, circleButton, triangleButton, deleteButton);
        hBox.setPadding(new Insets(5));
        hBox.setSpacing(5.0);
        hBox.getStyleClass().add("toolbar");
        root.setTop(hBox);


        createStatusBar();


        primaryStage.setTitle("Drawing");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void createStatusBar() {
        final StatusBarShapesObserver statusBarShapesObserver = StatusBarShapesObserver.createEmpty();
        statusBarShapesObserver.getStyleClass().add("status_bar");
        root.setBottom(statusBarShapesObserver);

        drawingPane.addObserver(statusBarShapesObserver);
    }


    public DrawingPane getDrawingPane() {
        return drawingPane;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
