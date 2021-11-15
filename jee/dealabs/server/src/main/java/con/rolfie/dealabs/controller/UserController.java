package con.rolfie.dealabs.controller;

import con.rolfie.dealabs.exception.UserNotFoundException;
import con.rolfie.dealabs.model.dto.input.NewUserDto;
import con.rolfie.dealabs.model.dto.output.UserDto;
import con.rolfie.dealabs.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/public/users")
    public final ResponseEntity<String> create(@RequestBody final NewUserDto user) {
        final var createdUser = userService.createAndSave(user);
        return ResponseEntity.created(URI.create("/users/" + createdUser.getId()))
                .build();
    }

    @GetMapping("/users/{id}")
    public final ResponseEntity<UserDto> fetchUser(@PathVariable("id") final long id) {
        try {
            return ResponseEntity.ok(userService.findById(id));
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound()
                    .build();
        }
    }


}
