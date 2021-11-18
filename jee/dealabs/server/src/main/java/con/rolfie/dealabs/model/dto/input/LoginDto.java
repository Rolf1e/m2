package con.rolfie.dealabs.model.dto.input;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginDto {

    private final String nickname;
    private final String password;

    @JsonCreator
    public static LoginDto create(@JsonProperty("nickname") final String nickname,
                                  @JsonProperty("password") final String password) {
        return new LoginDto(nickname, password);
    }
}
