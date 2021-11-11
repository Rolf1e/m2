package con.rolfie.dealabs.service.user;

import con.rolfie.dealabs.exception.UserNotFoundException;
import con.rolfie.dealabs.model.database.entity.UserDo;

public interface UserService {

    UserDo findById(final Long id) throws UserNotFoundException;
}
