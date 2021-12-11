package drawing;

import drawing.bar.tool.ToolBar;
import drawing.bar.status.StatusBar;
import drawing.panes.DrawingPane;
import drawing.utils.ResourcesUtils;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class PaintApplication extends Application {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    private Scene scene;
    private BorderPane root;
    private DrawingPane drawingPane;

    @Override
    public void start(Stage primaryStage) {
        root = new BorderPane();
        scene = new Scene(root, WIDTH, HEIGHT);

        root.getStylesheets().add(ResourcesUtils.loadResource("style/Paint.css"));
        drawingPane = new DrawingPane();
        drawingPane.getStyleClass().add("drawingPane");
        root.setCenter(drawingPane);

        HBox hBox = new HBox();
        createToolBar(hBox);

        hBox.setPadding(new Insets(5));
        hBox.setSpacing(5.0);
        hBox.getStyleClass().add("toolbar");
        root.setTop(hBox);

        createStatusBar();

        primaryStage.setTitle("Drawing");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void createToolBar(HBox hBox) {
        final var toolBar = ToolBar.createWithButtons(drawingPane);
        hBox.getChildren()
                .addAll(toolBar.getButtons());
    }

    private void createStatusBar() {
        final var statusBar = StatusBar.createEmpty();
        statusBar.getStyleClass()
                .add("status_bar");
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
