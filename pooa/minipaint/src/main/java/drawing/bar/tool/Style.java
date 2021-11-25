package drawing.bar.tool;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Style {

    private final Type type;
    private final String value;

    public static Style create(final Type style,
                               final String value) {
        return new Style(style, value);
    }

    public enum Type {
        TEXT_ONLY,
        ICON_ONLY
    }
}
