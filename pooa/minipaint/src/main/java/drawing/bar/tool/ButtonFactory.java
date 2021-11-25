package drawing.bar.tool;

import drawing.panes.DrawingPane;
import drawing.utils.ResourcesUtils;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
class ButtonFactory {

    private final DrawingPane drawingPane;

    public static ButtonFactory create(final DrawingPane drawingPane) {
        return new ButtonFactory(drawingPane);
    }

    public Button createJavaFxButtonWith(final ToolBarButtonsConfig config) {
        final var style = config.getStyle();
        final var button = buildButton(style);
        button.addEventFilter(config.getEvent(), config.getHandler(drawingPane));
        return button;
    }

    private Button buildButton(Style style) {
        switch (style.getType()) {
            case TEXT_ONLY:
                return new Button(style.getValue());
            case ICON_ONLY:
                return iconButton(style);
            default:
                throw new IllegalStateException("Invalid config");
        }

    }

    private Button iconButton(final Style style) {
        final var button = new Button();
        final var imageView = new ImageView(ResourcesUtils.loadResource(style.getValue()));
        button.setGraphic(imageView);
        return button;
    }

}
