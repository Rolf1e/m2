package drawing.bar.tool;

import drawing.bar.tool.conf.ToolBarButtonsConfig;
import drawing.bar.tool.conf.ToolBarComboBoxConfig;
import drawing.panes.DrawingPane;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
    @Getter
    private final List<ComboBox<String>> linesType;

    /**
     * Create the toolbar with the button config from {@link ToolBarButtonsConfig}
     * and add them to the given drawing pane
     */
    public static ToolBar createWithButtons(final DrawingPane drawingPane) {
        final var factory = ButtonFactory.create(drawingPane);
        final var buttons = Arrays.stream(ToolBarButtonsConfig.values())
                .map(factory::createJavaFxButtonWith)
                .collect(Collectors.toList());

        final var linesType = Arrays.stream(ToolBarComboBoxConfig.values())
                .map(config -> {
                    final var comboBox = config.getComboxBox();
                    ToolBarComboBoxConfig.addEventListener(comboBox, config.getListener(drawingPane));
                    return comboBox;
                })
                .collect(Collectors.toList());


        return new ToolBar(buttons, linesType);
    }

}
