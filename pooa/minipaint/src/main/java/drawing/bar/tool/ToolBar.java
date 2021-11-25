package drawing.bar.tool;

import drawing.panes.DrawingPane;
import javafx.scene.control.Button;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ToolBar {

    @Getter
    private final List<Button> buttons;

    /**
     * Create the toolbar with the button config from {@link ToolBarButtonsConfig}
     * and add them to the given drawing pane
     */
    public static ToolBar createWithButtons(final DrawingPane drawingPane) {
        final var factory = ButtonFactory.create(drawingPane);
        final var buttons = Arrays.stream(ToolBarButtonsConfig.values())
                .map(factory::createJavaFxButtonWith)
                .collect(Collectors.toList());
        return new ToolBar(buttons);
    }

}
