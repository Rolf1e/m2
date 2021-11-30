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
        final var button = buildButton(config);
        button.addEventFilter(config.getEvent(), config.getHandler(drawingPane));
        return button;
    }

    private Button buildButton(final ToolBarButtonsConfig config) {
        final Style style = config.getStyle();
        switch (style.getType()) {
            case TEXT_ONLY:
                return new Button(config.getName());
            case ICON_ONLY:
                return iconButton(config.getName(), style);
            default:
                throw new IllegalStateException("Invalid config");
        }

    }

    private Button iconButton(final String name,
                              final Style style) {
        final var button = new Button(name);
        final var imageView = new ImageView(ResourcesUtils.loadResource(style.getValue()));
        button.setGraphic(imageView);
        return button;
    }

}
