package con.rolfie.dealabs.model.dto.output;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import con.rolfie.dealabs.model.database.entity.UserDo;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDto {

    private final Long id;
    private final String nickname;
    private final String firstName;
    private final String lastName;
    @JsonIgnore
    private final UserDo userDo;

    @JsonCreator
    public static UserDto create(@JsonProperty("id") final Long id,
                                 @JsonProperty("nickname") final String nickname,
                                 @JsonProperty("first_name") final String firstName,
                                 @JsonProperty("last_name") final String lastName) {
        return new UserDto(id, nickname, firstName, lastName, null);
    }

    public static UserDto from(final UserDo user) {
        return new UserDto(user.getId(), user.getNickname(), user.getFirstName(), user.getLastName(), user);
    }
}
