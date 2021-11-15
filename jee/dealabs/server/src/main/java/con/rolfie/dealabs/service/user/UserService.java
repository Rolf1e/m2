package con.rolfie.dealabs.service.user;

import con.rolfie.dealabs.exception.UserNotFoundException;
import con.rolfie.dealabs.model.database.entity.UserDo;
import con.rolfie.dealabs.model.dto.input.NewUserDto;
import con.rolfie.dealabs.model.dto.output.UserDto;

import java.util.Optional;

public interface UserService {

    UserDto findById(final Long id) throws UserNotFoundException;

    UserDto findByFirstName(final String name) throws UserNotFoundException;

    UserDto createAndSave(final NewUserDto user);
}
