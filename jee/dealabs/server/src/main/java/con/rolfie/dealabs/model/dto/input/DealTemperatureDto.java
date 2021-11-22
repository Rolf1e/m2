package con.rolfie.dealabs.model.dto.input;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class DealTemperatureDto {

    private final Long dealId;
    private final String nickname;
    private final Direction direction;

    @JsonCreator
    public static DealTemperatureDto create(@JsonProperty("deal_id") final Long dealId,
                                            @JsonProperty("nickname") final String nickname,
                                            @JsonProperty("direction") final Direction direction) {

        return new DealTemperatureDto(dealId, nickname, direction);
    }

    public enum Direction {
        UP, DOWN
    }
}
