package con.rolfie.dealabs.service.user;

import con.rolfie.dealabs.exception.UserNotFoundException;
import con.rolfie.dealabs.model.database.dao.UserRepository;
import con.rolfie.dealabs.model.database.entity.UserDo;
import con.rolfie.dealabs.model.dto.input.NewUserDto;
import con.rolfie.dealabs.model.dto.output.UserDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.Collections;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class SqlUserService implements UserService {

    private final UserRepository userRepository;

    public static UserService create(final UserRepository userRepository) {
        return new SqlUserService(userRepository);
    }

    @Override
    public UserDto findById(final Long id) throws UserNotFoundException {
        return userRepository.findById(id)
                .map(UserDto::from)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public UserDto findByNickNameAndPassword(final String nickname, final String password) throws UserNotFoundException {
        return userRepository.findByNicknameAndPassword(nickname, password)
                .map(UserDto::from)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public UserDto createAndSave(final NewUserDto user) {
        return UserDto.from(userRepository.save(createFromInput(user)));
    }

    private UserDo createFromInput(final NewUserDto user) {
        final var tobeSaved = new UserDo();
        tobeSaved.setNickname(user.getNickname());
        tobeSaved.setLastName(user.getLastName());
        tobeSaved.setFirstName(user.getFirstName());
        tobeSaved.setPassword(user.getPassword());
        tobeSaved.setDeals(Collections.emptyList());

        return tobeSaved;
    }
}
