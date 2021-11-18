package con.rolfie.dealabs.model.dto.input;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class NewUserDto {

    private final String nickname;
    private final String firstName;
    private final String lastName;
    private final String password;

    @JsonCreator
    public static NewUserDto create(@JsonProperty("nickname") final String nickname,
                                    @JsonProperty("first_name") final String firstName,
                                    @JsonProperty("last_name") final String lastName,
                                    @JsonProperty("password") final String password) {

        return new NewUserDto(nickname, firstName, lastName, password);
    }
}
