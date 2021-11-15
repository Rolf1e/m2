package con.rolfie.dealabs.security.provider;

import con.rolfie.dealabs.exception.UserNotFoundException;
import con.rolfie.dealabs.model.dto.output.UserDto;
import con.rolfie.dealabs.model.security.AppUser;
import con.rolfie.dealabs.service.user.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.Collections;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class UserAuthenticationProvider implements AuthenticationProvider {

    private final UserService userService;

    public static UserAuthenticationProvider create(final UserService userService) {
        return new UserAuthenticationProvider(userService);
    }

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
        final var name = authentication.getName();
        try {
            final var credentials = (String) authentication.getCredentials();
            final var byFirstName = userService.findByFirstName(name);
            return new UsernamePasswordAuthenticationToken(
                    AppUser.create(byFirstName.getFirstName(), credentials, getAuthorities()),
                    credentials,
                    getAuthorities()
            );
        } catch (UserNotFoundException e) {
            throw new UsernameNotFoundException("Can not find user " + name);
        }
    }

    private Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public boolean supports(final Class<?> authenticate) {
        return authenticate.equals(UsernamePasswordAuthenticationToken.class);
    }
}
