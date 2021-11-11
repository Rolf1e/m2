package con.rolfie.dealabs.service.user;

import con.rolfie.dealabs.exception.UserNotFoundException;
import con.rolfie.dealabs.model.database.dao.UserRepository;
import con.rolfie.dealabs.model.database.entity.UserDo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public static UserService create(final UserRepository userRepository) {
        return new UserServiceImpl(userRepository);
    }

    @Override
    public UserDo findById(final Long id) throws UserNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }
}
