package drawing;

import drawing.bar.status.ErrorBar;
import drawing.bar.status.StatusBar;
import drawing.bar.tool.ToolBar;
import drawing.panes.DrawingPane;
import drawing.utils.ResourcesUtils;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

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

        final var top = new HBox();
        createToolBar(top);

        top.setPadding(new Insets(5));
        top.setSpacing(5.0);
        top.getStyleClass().add("toolbar");
        root.setTop(top);

        final var bottom = new HBox();
        createStatusBar(bottom);
        root.setBottom(bottom);

        primaryStage.setTitle("Drawing");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void createToolBar(final HBox hBox) {
        final var toolBar = ToolBar.createWithButtons(drawingPane);
        final List<Node> nodes = new ArrayList<>(toolBar.getButtons());
        nodes.addAll(toolBar.getLinesType());
        hBox.getChildren()
                .addAll(nodes);
    }

    private void createStatusBar(final HBox hBox) {
        final var statusBar = StatusBar.createEmpty();
        statusBar.getStyleClass()
                .add("status_bar");
        final var errorBar = ErrorBar.createEmpty();
        statusBar.getStyleClass()
                .add("error_bar");
        hBox.getChildren().addAll(statusBar, errorBar);
        drawingPane.addObserver(statusBar);
        drawingPane.addObserver(errorBar);
    }

    public DrawingPane getDrawingPane() {
        return drawingPane;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
