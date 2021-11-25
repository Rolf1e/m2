package drawing.utils;

import lombok.experimental.UtilityClass;

import java.util.Objects;

@UtilityClass
public class ResourcesUtils {

    public String loadResource(final String location) {
        final var classLoader = ResourcesUtils.class.getClassLoader();
        return Objects.requireNonNull(classLoader.getResource(location))
                .toExternalForm();
    }

}
