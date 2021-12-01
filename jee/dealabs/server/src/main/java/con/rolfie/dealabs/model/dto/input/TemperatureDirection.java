package con.rolfie.dealabs.model.dto.input;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TemperatureDirection {
    UP(1), DOWN(-1);

    private final Integer value;

}
